package com.my.infrastructure.security.jwt;

import com.my.infrastructure.security.TokenProvider;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final String SECRET_KEY = "MY_JWT_TOKEN_KEY";

    @Override
    public String create(String userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + TokenExpirationTime.SEVEN_DAYS.getValue());
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expireDate)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    @Override
    public String getUserIdFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    @Override
    public void validate(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new RuntimeException("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            throw new RuntimeException("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Expired JWT token", ex);
        } catch (UnsupportedJwtException ex) {
            throw new RuntimeException("Unsupported JWT token", ex);
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("JWT claims string is empty.", ex);
        }
    }
}
