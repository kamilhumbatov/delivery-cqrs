package com.delivery.user.dto.signup;

import com.delivery.user.util.StrongPassword;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Name must not be empty!")
    @Size(min = 4, max = 40, message = "Name size must be between 4 and 40")
    @ApiModelProperty(value = "Password", example = "Kamil Humbatov")
    private String fullName;


    @StrongPassword
    @NotBlank(message = "Username must not be empty!")
    @Size(min = 3, max = 15, message = "Username size must be between 3 and 15")
    @ApiModelProperty(value = "Password", example = "kamil")
    private String username;

    @NotBlank(message = "Password must not be empty!")
    @Size(min = 6, max = 20, message = "Password size must be between 6 and 20")
    @ApiModelProperty(value = "Password", example = "123456")
    private String password;
}
