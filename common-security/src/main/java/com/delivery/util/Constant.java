package com.delivery.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };
}