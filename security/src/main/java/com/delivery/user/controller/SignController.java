package com.delivery.user.controller;

import com.delivery.user.dto.auth.AuthRequest;
import com.delivery.user.dto.auth.AuthResponse;
import com.delivery.user.dto.auth.RefreshTokenRequest;
import com.delivery.user.dto.signup.SignUpRequest;
import com.delivery.user.dto.signup.SignUpResponse;
import com.delivery.user.service.AuthService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api("Sign API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SignController {

    private final AuthService authService;

    @PostMapping(value = "/auth")
    public AuthResponse doAuth(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        return authService.doAuth(authRequest);
    }

    @PostMapping("/signup/user")
    public SignUpResponse registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
        log.info("User trying signup", signUpRequest.getUsername());
        return SignUpResponse.builder()
                .result("User successfully created")
                .build();
    }

    @PostMapping(value = "/refresh-token")
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        //return refreshTokenService.refreshToken(request);
        return null;
    }
}
