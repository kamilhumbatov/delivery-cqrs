package com.delivery.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    public static final String[] METHODS = {"GET", "POST", "PUT", "OPTIONS", "DELETE"};

    public static final String[] ALLOW_HEADERS = {"Authorization", "Cache-Control",
            "Content-Type", "Accept-Language"
    };

    public static final String[] SWAGGER_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
    };

    public static final String SECRET_KEY="6cvdeT8HJsvYHjhvDHsmFRMVf8e2yQyC";
}
