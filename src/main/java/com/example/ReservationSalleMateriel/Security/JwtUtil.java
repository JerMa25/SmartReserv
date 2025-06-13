package com.example.ReservationSalleMateriel.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.example.ReservationSalleMateriel.model.Enseignant;

import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwtUtil {

    private final String SECRET_STRING = "your_super_secure_secret_that_is_long_enough_32_bytes";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public String generateToken(Enseignant enseignant) {
        return Jwts.builder()
                .setSubject(enseignant.getEmail())
                .claim("matricule", enseignant.getMatricule())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, Enseignant enseignant) {
        final String email = extractEmail(token);
        return (email.equals(enseignant.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
