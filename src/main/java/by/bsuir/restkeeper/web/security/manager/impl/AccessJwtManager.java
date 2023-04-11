package by.bsuir.restkeeper.web.security.manager.impl;

import by.bsuir.restkeeper.service.property.JwtProperty;
import by.bsuir.restkeeper.web.security.manager.JwtManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AccessJwtManager implements JwtManager {

    private static Key accessKey;
    private final JwtProperty jwtProperty;

    @PostConstruct
    private void setAccessKey() {
        accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.jwtProperty.getAccessKey()));
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .claim("role", userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * this.jwtProperty.getAccessExpirationTime()))
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            return this.extractClaim(token, Claims::getExpiration).after(new Date(System.currentTimeMillis()));
        } catch (Exception ignored) {
        }
        return false;
    }

    @Override
    public  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
