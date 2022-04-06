package com.delivery.user.controller;

import com.delivery.security.util.RoleName;
import com.delivery.user.dto.auth.AuthRequest;
import com.delivery.user.dto.auth.AuthResponse;
import com.delivery.user.dto.signup.SignUpRequest;
import com.delivery.user.dto.signup.SignUpResponse;
import com.delivery.user.service.AuthService;
import com.delivery.user.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
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
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping(value = "/auth")
    public AuthResponse doAuth(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        return authService.doAuth(authRequest);
    }

    @PostMapping("/signup/customer")
    public SignUpResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("Customer trying signup", signUpRequest.getUsername());
        return SignUpResponse.builder()
                .result(userService.createCustomer(signUpRequest))
                .build();
    }

    @Secured(RoleName.ROLE_ADMIN)
    @PostMapping("/signup/courier")
    public SignUpResponse registerCourier(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("Customer trying signup", signUpRequest.getUsername());
        return SignUpResponse.builder()
                .result(userService.createCustomer(signUpRequest))
                .build();
    }
}
