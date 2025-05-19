package com.auth_service.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;
public class JwtUtil {
    private static final String SECRET = "mysecretkey";
    private static final long EXP = 3600_000; // 1h
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    public static String generateToken(String userId, String role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    /**
     * Parse token để lấy Claims (payload)
     */
    public static Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Lấy userId từ token
     */
    public static String getUserId(String token) {
        return parseToken(token).getSubject();
    }

    /**
     * Lấy role từ token
     */
    public static String getUserRole(String token) {
        return (String) parseToken(token).get("role");
    }
}
