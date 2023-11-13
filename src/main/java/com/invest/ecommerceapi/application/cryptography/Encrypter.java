package com.invest.ecommerceapi.application.cryptography;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class Encrypter {
    private Key secret;

    @PostConstruct
    public void init() {
        String secretKey = "12345678901234567890123456789012";
        this.secret = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String encrypt(Object input) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 86400000);
        return Jwts.builder().subject((String) input).issuedAt(now).expiration(expirationDate).signWith(this.secret).compact();
    }

    public Object decrypt(String input) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).build().parseClaimsJws(input).getBody();
        return claims.getSubject();
    }
}
