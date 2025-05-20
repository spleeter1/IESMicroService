package com.api_gateway.util;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;

public class JwtUtils {

    public static JWTClaimsSet extractClaims(String token) throws Exception {
        JWSObject jws = JWSObject.parse(token);
        return JWTClaimsSet.parse(jws.getPayload().toJSONObject());
    }

    public static String getSubject(String token) throws Exception {
        return extractClaims(token).getSubject();
    }
}
