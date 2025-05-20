package com.api_gateway.filter;

import com.api_gateway.config.JwtProperties;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Order(-1)
public class JwtAuthFilter implements GlobalFilter {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtAuthFilter(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Bỏ qua một số route không cần xác thực
        if (path.startsWith("/users/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange);
        }

        String token = authHeader.substring(7);

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            MACVerifier verifier = new MACVerifier(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));

            if (!jwsObject.verify(verifier)) {
                return unauthorized(exchange);
            }

            // (Optional) In payload
            String payload = jwsObject.getPayload().toString();
            System.out.println("✅ JWT payload: " + payload);

            return chain.filter(exchange);

        } catch (Exception e) {
            e.printStackTrace();
            return unauthorized(exchange);
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
