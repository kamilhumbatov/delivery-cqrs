package com.delivery.user.dto.auth;

import com.delivery.user.util.StrongPassword;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @NotBlank
    @StrongPassword
    @ApiModelProperty(value = "Username", example = "kamil")
    private String username;

    @NotBlank
    @ApiModelProperty(value = "Password", example = "123456")
    private String password;
}