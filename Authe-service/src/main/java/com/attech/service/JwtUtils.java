package com.attech.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JwtUtils {

    @Value("${jwt.secret}")
    String jwtSecret;

    @Value("${jwt.expiration}")
    String jwtTime;

    public String generate(String userId, String role, String tokenType) {
        Map<String, Object> claims = Map.of("userId", userId, "role", List.of(role, "USER"));
        long expInMillis = "ACCESS".equals(tokenType)
                ? Long.parseLong(jwtTime) * 1000
                : Long.parseLong(jwtTime) * 1000 * 5;

        Date now = new Date();
        Date exp = new Date(now.getTime() * expInMillis);

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setExpiration(exp)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

}