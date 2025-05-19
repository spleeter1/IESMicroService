package com.user_service.Security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private final byte[] secret;
    private final Long expiration;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret,
                        @Value("${jwt.expiration}") Long expiration) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        this.expiration = expiration;
    }

    public String generateToken(UserDetails userDetails) {
        try {
            // Kiểm tra độ dài khóa (phải đủ dài nếu dùng HS512 — tối thiểu 64 bytes)
            if (secret.length < 64) {
                throw new IllegalArgumentException("JWT secret too short for HS512 (must be at least 64 bytes)");
            }

            // Tạo thông tin claims
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userDetails.getUsername())
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + expiration * 1000))
                    .build();

            // Tạo header và ký token
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);
            signedJWT.sign(new MACSigner(secret));

            return signedJWT.serialize();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token", e);
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}
