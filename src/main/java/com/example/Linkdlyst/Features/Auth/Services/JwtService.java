package com.example.Linkdlyst.Features.Auth.Services;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.Linkdlyst.Features.Auth.Entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final SecretKey secretKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtService(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt-access-token-expiration}") long accessTokenExpiration,
        @Value("${jwt-refresh-token-expiration}") long refreshTokenExpiration
    ){
        
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public String generateAccessToken(UserEntity user){
        return Jwts.builder()
        .subject(user.getEmail())
        .claim("userId", user.getId())
        .claim("type", "accesss")
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+accessTokenExpiration))
        .signWith(secretKey)
        .compact();
    }

    public String generateRefreshToken(UserEntity user){
        return Jwts.builder()
        .subject(user.getEmail())
        .claim("userId", user.getId())
        .claim("type", "refresh")
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis()+refreshTokenExpiration))
        .signWith(secretKey)
        .compact();
    }

}
