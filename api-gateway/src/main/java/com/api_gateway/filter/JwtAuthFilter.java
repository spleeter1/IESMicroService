package com.api_gateway.filter;

import com.api_gateway.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class JwtAuthFilter implements WebFilter {
    private static final Logger logger = Logger.getLogger(JwtAuthFilter.class.getName());

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();

        logger.info("Đang xử lý request: " + method + " " + path);

        // Bỏ qua xác thực cho các đường dẫn cụ thể
        if (path.startsWith("/users/auth/")) {
            logger.info("Bỏ qua xác thực cho đường dẫn auth: " + path);
            return chain.filter(exchange);
        }

        // Bỏ qua request OPTIONS
        if ("OPTIONS".equals(method)) {
            logger.info("Bỏ qua xác thực cho request OPTIONS");
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        logger.info("Header Authorization: " + (authHeader != null ?
                authHeader.substring(0, Math.min(15, authHeader.length())) + "..." : "null"));

        if (authHeader == null) {
            logger.warning("Thiếu header Authorization");
            return createErrorResponse(exchange, "Thiếu header Authorization", HttpStatus.UNAUTHORIZED);
        }

        if (!authHeader.startsWith("Bearer ")) {
            logger.warning("Định dạng header Authorization không hợp lệ, phải bắt đầu bằng 'Bearer '");
            return createErrorResponse(exchange, "Định dạng header Authorization không hợp lệ", HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        try {
            // Gỡ lỗi token trước khi xác thực
            jwtUtil.debugToken(token);

            // Xác thực token
            var claims = jwtUtil.validateToken(token);
            logger.info("Xác thực token thành công cho subject: " + claims.getSubject());

            // Lưu claims vào thuộc tính exchange
            exchange.getAttributes().put("claims", claims);
            exchange.getAttributes().put("userId", claims.getSubject());

            return chain.filter(exchange);
        } catch (Exception e) {
            logger.severe("Xác thực token thất bại: " + e.getMessage());
            return createErrorResponse(exchange, "Xác thực token thất bại: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    private Mono<Void> createErrorResponse(ServerWebExchange exchange, String message, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String errorJson = "{\"error\":\"" + message + "\"}";
        byte[] bytes = errorJson.getBytes();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
}