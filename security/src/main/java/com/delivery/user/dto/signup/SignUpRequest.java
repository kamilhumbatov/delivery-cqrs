package com.delivery.user.dto.signup;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank(message = "Name must not be empty!")
    @Size(min = 4, max = 40, message = "Name size must be between 4 and 40")
    private String fullName;

    @NotBlank(message = "Username must not be empty!")
    @Size(min = 3, max = 15, message = "Username size must be between 3 and 15")
    private String username;

    @NotBlank(message = "Password must not be empty!")
    @Size(min = 6, max = 20, message = "Password size must be between 6 and 20")
    private String password;
}
