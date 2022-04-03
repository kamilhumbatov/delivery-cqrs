package com.delivery.user.config;

import com.delivery.AuthEntryPointJwt;
import com.delivery.JwtTokenFilter;
import com.delivery.deliver.config.MainSecurityConfig;
import com.delivery.user.service.impl.CustomUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    @SneakyThrows
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)  {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers("/api/v1/auth",
                        "/api/v1/signup/customer",
                        "/api/v1/refresh-token").permitAll()
                .anyRequest().authenticated();
    }
}
