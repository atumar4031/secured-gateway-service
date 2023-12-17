package com.attech.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtils {

    @Value("${jwt.secret}")
    String jwtSecret;
    public boolean isExpired(String token) {
        return getExpiration(token).before(new Date());
    }
    public Date getExpiration(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token.substring("Bearer ".length())).getBody();
    }


}