package com.yassine.bookshop.services;


import com.yassine.bookshop.entities.Role;
import com.yassine.bookshop.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${app.jwt.expiration}")
    private long tokenAccessExpirationTime;
    @Value("${app.jwt.secret}")
    private String secretKey;
    @Value("${app.jwt.tokenRefreshExpirationTime}")
    private long tokenRefreshExpirationTime;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user, HttpServletRequest request) {
        return generateToken(user,tokenAccessExpirationTime, request);

    }

    public String generateRefreshToken(User user, HttpServletRequest request) {
        return generateToken(user,tokenRefreshExpirationTime ,request);

    }

    public String generateToken(User user , long tokenExpirationTime, HttpServletRequest request) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("user_ip", request.getRemoteAddr())
                .claim("Role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpirationTime))
                .signWith(signingKey)
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            var claims = Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractClaim(String tokenWithoutBearer, String userIp) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(tokenWithoutBearer)
                .getPayload().get(userIp, String.class);
    }

    public Role getRole(String tokenWithoutBearer) {
        return Role.valueOf(getPayload(tokenWithoutBearer).get("Role", String.class));
    }

    private Claims getPayload(String tokenWithoutBearer) {
        return Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(tokenWithoutBearer).getPayload();
    }
}
