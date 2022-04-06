package com.delivery.user.dto.signup;

import com.delivery.user.util.Constants;
import com.delivery.user.util.StrongPassword;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Name must not be empty!")
    @Size(min = 4, max = 40, message = "Name size must be between 4 and 40")
    @ApiModelProperty(value = "Fullname", example = "Kamil Humbatov")
    private String fullName;


    @NotBlank(message = "Username must not be empty!")
    @Pattern(regexp = Constants.EMAIL_FORMAT, message = "Email Address is not a valid format")
    @ApiModelProperty(value = "Username", example = "kamil@kamil.com")
    private String username;

    @StrongPassword
    @NotBlank(message = "Password must not be empty!")
    @ApiModelProperty(value = "Password", example = "a123456A")
    private String password;
}
