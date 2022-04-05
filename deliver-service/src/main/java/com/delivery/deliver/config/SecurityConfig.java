package com.delivery.deliver.config;

import com.delivery.security.AuthEntryPointJwt;
import com.delivery.security.JwtTokenFilter;
import com.delivery.security.config.MainSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
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
                .anyRequest().authenticated();
    }
}
