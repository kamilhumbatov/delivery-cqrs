package com.delivery;

import com.delivery.model.CustomUser;
import com.delivery.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtService {

    private static final String ID = "id";
    private static final String ROLE_CEO = "ROLE_CEO";
    private static final String TIN = "TIN";
    private static final String secret = "X1X2X3";

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public CustomUserDetails createUserDetails(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return createUser(claims);
    }

    private CustomUserDetails createUser(Claims claims) {
        return new CustomUserDetails(
                CustomUser.builder()
                        .username(claims.getSubject())
                        .password((String) claims.get(ID))
                        .tin((String) claims.get(TIN))
                        .role(ROLE_CEO)
                        .build());
    }

    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token - {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token - {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token - {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty - {}", e.getMessage());
        }
        return false;
    }
}