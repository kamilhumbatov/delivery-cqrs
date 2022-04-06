package com.delivery.user.dto.auth;

import com.delivery.user.util.Constants;
import com.delivery.user.util.StrongPassword;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthRequest {

    @NotBlank
    @Pattern(regexp = Constants.EMAIL_FORMAT, message = "Email Address is not a valid format")
    @ApiModelProperty(value = "Username", example = "kamil")
    private String username;

    @NotBlank
    @StrongPassword
    @ApiModelProperty(value = "Password", example = "a123456A")
    private String password;
}