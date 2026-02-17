package com.example.gateway.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {


    private String secret ="840OUHb4vZMQLfDTg0PPpn6vdIbHItcAwfAJw30nE/Y=";

    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isTokenExpired(String token) {


        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token) {


        try {
            this.getAllClaimsFromToken(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return true;
        }
    }

    public String extractUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }
}