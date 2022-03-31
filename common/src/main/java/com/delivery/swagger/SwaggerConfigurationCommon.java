package com.delivery.swagger;

import com.google.common.collect.Lists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

public class SwaggerConfigurationCommon {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    private static final String CODE_404 = "The resource you were trying to reach is not found";
    private static final String CODE_403 = "Accessing the resource you were trying to reach is forbidden!";
    private static final String CODE_401 = "Full authentication is required to access this resource";
    private static final String CODE_400 = "Front error!";
    private static final String CODE_200 = "Successfully retrieved";

    public Docket create(String title, String name, String pack) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(title))
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .groupName(name)
                .select()
                .apis(RequestHandlerSelectors.basePackage(pack))
                .build()
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getResponseMessage())
                .globalResponseMessage(RequestMethod.POST, getResponseMessage())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo(String title) {
        return new ApiInfo(
                title,
                "Parcel Delivery",
                "1.0.0",
                "",
                new Contact("", "", ""),
                "", "", Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }

    private List<ResponseMessage> getResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(404).message(CODE_404).build(),
                new ResponseMessageBuilder().code(403).message(CODE_403).build(),
                new ResponseMessageBuilder().code(401).message(CODE_401).build(),
                new ResponseMessageBuilder().code(400).message(CODE_400).build(),
                new ResponseMessageBuilder().code(200).message(CODE_200).build()
        );
    }
}
