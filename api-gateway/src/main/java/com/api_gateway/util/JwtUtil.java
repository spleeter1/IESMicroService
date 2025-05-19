package com.api_gateway.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Component
public class JwtUtil {
    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());

    private final byte[] secret;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Đảm bảo mã bí mật được mã hóa đúng cách
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        logger.info("Độ dài JWT Secret: " + this.secret.length);
    }

    public JWTClaimsSet validateToken(String token) throws Exception {
        try {
            logger.info("Đang xác thực token: " + token.substring(0, Math.min(10, token.length())) + "...");

            // Phân tích đối tượng JWS
            JWSObject jwsObject = JWSObject.parse(token);
            logger.info("Phân tích token thành công");

            // Tạo bộ xác minh với mã bí mật
            MACVerifier verifier = new MACVerifier(secret);
            logger.info("Đã tạo bộ xác minh");

            // Xác minh chữ ký
            boolean verified = jwsObject.verify(verifier);
            logger.info("Kết quả xác minh chữ ký: " + verified);

            if (!verified) {
                throw new JOSEException("Xác minh chữ ký thất bại");
            }

            // Phân tích claims
            JWTClaimsSet claims = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
            logger.info("Đã phân tích claims: " + claims.toJSONObject().keySet());

            // Kiểm tra hết hạn
            if (claims.getExpirationTime() == null) {
                logger.warning("Token không có thời gian hết hạn");
                throw new Exception("Token không có thời gian hết hạn");
            }

            if (claims.getExpirationTime().before(new java.util.Date())) {
                logger.warning("Token đã hết hạn vào " + claims.getExpirationTime());
                throw new Exception("Token đã hết hạn");
            }

            logger.info("Xác thực token thành công");
            return claims;
        } catch (Exception e) {
            logger.severe("Lỗi xác thực: " + e.getMessage());
            throw e;
        }
    }

    // Thêm phương thức để in chi tiết token để gỡ lỗi
    public void debugToken(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            logger.info("Header: " + jwsObject.getHeader().toJSONObject());
            logger.info("Payload: " + jwsObject.getPayload().toJSONObject());
        } catch (Exception e) {
            logger.severe("Không thể gỡ lỗi token: " + e.getMessage());
        }
    }
}