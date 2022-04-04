package com.delivery.deliver.config;

import com.delivery.swagger.SwaggerConfigurationCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends SwaggerConfigurationCommon {

    private static final String TITLE = "Deliver Backend Services";

    @Bean
    public Docket online() {
        return create(TITLE, "Deliver", "com.delivery.deliver.controller");
    }
}