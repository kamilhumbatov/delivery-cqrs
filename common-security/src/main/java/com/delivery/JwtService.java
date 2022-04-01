package com.delivery;

import com.delivery.model.CustomUser;
import com.delivery.model.CustomUserDetails;
import com.delivery.util.PublicKeyUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;

@Slf4j
@Component
@Import({PublicKeyUtility.class})
@RequiredArgsConstructor
public class JwtService {

    private static final String ID = "id";
    private static final String ROLE_CEO = "ROLE_CEO";
    private static final String TIN = "TIN";

    private final RSAPublicKey publicKey;

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
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
                    .setSigningKey(publicKey)
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
