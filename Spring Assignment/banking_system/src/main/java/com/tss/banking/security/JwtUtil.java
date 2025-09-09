package com.tss.banking.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tss.banking.exception.BankApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${app.jwt-secret:bXlTZWNyZXRLZXlGb3JCYW5raW5nU3lzdGVtVGhhdElzVmVyeVNlY3VyZUFuZExvbmc=}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds:86400000}")
    private long jwtExpirationDate;

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expireDate)
                .and()
                .signWith(key())
                .compact();

        return token;
    }

    public String generateToken(String username, String role) {
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .claims()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expireDate)
                .and()
                .signWith(key())
                .claim("role", role)
                .compact();

        return token;
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        return claims.getSubject();
    }

    public String extractRole(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        return claims.get("role", String.class);
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        return claims.getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new BankApiException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BankApiException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BankApiException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BankApiException("JWT claims string is empty");
        } catch (Exception e) {
            throw new BankApiException("Invalid Credentials");
        }
    }
}
