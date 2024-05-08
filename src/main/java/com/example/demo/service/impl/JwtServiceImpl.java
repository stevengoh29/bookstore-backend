package com.example.demo.service.impl;

import com.example.demo.model.app.User;
import com.example.demo.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    public Key getSecretKey () {
        String SECRET_KEY = "TEVEIFNNQVJUIEhPTUUgU0VDUkVUIEtFWSBGT1IgQVVUSEVOVElDQVRJT04gSldUIEdFVCBLRVkgTUVUSE9E";
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    @Override
    public String createJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        long tokenExpiration = new Date().getTime() + 172800000;

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(tokenExpiration))
                .signWith(getSecretKey())
                .compact();
    }
}
