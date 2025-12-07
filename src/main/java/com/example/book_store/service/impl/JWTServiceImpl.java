package com.example.book_store.service.impl;

import com.example.book_store.service.JWTService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class JWTServiceImpl implements JWTService {
    private String secretKey = "";

    public JWTServiceImpl() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey1 = keyGenerator.generateKey();
        secretKey = Base64.getEncoder().encodeToString(secretKey1.getEncoded());
    }

    @Override
    public String generateToken(String userName) {

        Map<String, Objects> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyValue = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyValue);
    }
}
