package com.delivery.user.service.impl;

import com.delivery.domain.User;
import com.delivery.user.dto.auth.AuthRequest;
import com.delivery.user.dto.auth.AuthResponse;
import com.delivery.user.exception.UserNotFoundException;
import com.delivery.user.service.AuthService;
import com.delivery.user.service.UserService;
import com.delivery.user.service.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse doAuth(AuthRequest authRequest) throws Exception {
        User user = userService.findByUsername(authRequest.getUsername()).orElseThrow(UserNotFoundException::new);
        authenticate(authRequest);
        return AuthResponse.builder()
                .accessToken(tokenProvider.generateTokenForUser(user))
                .refreshToken(tokenProvider.generateTokenForRefreshToken(user))
                .build();
    }

    private void authenticate(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
