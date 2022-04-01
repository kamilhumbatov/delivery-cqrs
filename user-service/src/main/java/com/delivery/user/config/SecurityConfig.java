package com.delivery.user.config;

import com.delivery.AuthEntryPointJwt;
import com.delivery.JwtTokenFilter;
import com.delivery.config.MainSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Import({JwtTokenFilter.class, AuthEntryPointJwt.class})
public class SecurityConfig extends MainSecurityConfig {

    public SecurityConfig(JwtTokenFilter jwtTokenFilter, AuthEntryPointJwt unauthorizedHandler) {
        super(jwtTokenFilter, unauthorizedHandler);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/api/v1/auth",
                        "/api/v1/signup/user",
                        "/api/v1/refresh-token").permitAll()
                .anyRequest().authenticated();
    }
}
