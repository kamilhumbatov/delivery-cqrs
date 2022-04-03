package com.delivery.user.service.security;

import com.delivery.deliver.config.SecurityProperties;
import com.delivery.user.domain.User;
import com.delivery.util.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@Import(SecurityProperties.class)
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String ROLE = "role";

    private final SecurityProperties securityProperties;

    public String generateTokenForUser(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put(ROLE, user.getRole().getDescription());
        return generateToken(3, claims);
    }

    public String generateTokenForRefreshToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put(ROLE, user.getRole().getDescription());
        return generateToken(3, claims);
    }

    private String generateToken(int multiply, Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + multiply * securityProperties.getExpireLength()))
                .signWith(SignatureAlgorithm.HS512, Constant.SECRET_KEY)
                .compact();
    }

}

