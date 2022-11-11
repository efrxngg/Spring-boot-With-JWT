package com.empresax.security.web.security.jwt;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// 3
public class JwtUtil {

    private static final String ACCESS_TOKEN_SECRET = "DevEFRXNGGeCOMMERCES3CR3TKEYW1THSPRINGS3CUR1TY4NDJWT";

    public static String generateToken(String username) {

        var ms = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(username)
                .setIssuedAt(new Date(ms))
                .setExpiration(new Date(ms + TimeUnit.HOURS.toMillis(10)))
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            return new UsernamePasswordAuthenticationToken(
                    getSubject(token),
                    null,
                    Collections.emptyList());
        } catch (JwtException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token).getBody();
    }

    public static String getSubject(String token) {
        return getClaims(token).getSubject();
    }
    
}
