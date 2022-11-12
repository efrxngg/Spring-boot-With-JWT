package com.empresax.security.web.security.jwt;

import static com.empresax.security.web.security.Constant.ROLE_CLAIM;
import static com.empresax.security.web.security.Constant.TIME_EXPIRED;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


// 3
@Component
public class JwtUtil {

    public static final String SECRET_KEY = "DevEFRXNGGeCOMMERCES3CR3TKEYW1THSPRINGS3CUR1TY4NDJWT";

    public String create(UserDetails principal) {
        Map<String, Object> payload = new HashMap<>();
        payload.put(ROLE_CLAIM, principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        payload.put("sub", principal.getUsername());
        var ms = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date(ms))
                .setExpiration(new Date(ms + TIME_EXPIRED))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token).getBody();
    }

}