package com.skilling.lms.shared.security;

import java.util.Date;
import java.util.Base64;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;

public class JwtUtil {

    private static final String SECRET_BASE64_STRING = "xg6nDGQWknmXuPoDy+/CU/PawwM+M/1SVug6xqnG7iw=";   
    private final SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_BASE64_STRING));

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            System.err.println("Error validating JWT Token: " + e.getMessage());
            return false;
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) 
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(secretKey)
                .compact();
    }

    public static void main(String... args) {
        MacAlgorithm alg = Jwts.SIG.HS256;
        SecretKey secretKey = alg.key().build();
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Clave Base64 Generado para HS256: " + base64Key);
    }
}
