package com.delivery.user.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @NotBlank
    @ApiModelProperty(value = "Username", example = "kamil")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "Password", example = "123")
    private String password;
}