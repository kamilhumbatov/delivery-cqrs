package com.delivery.user.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@NotBlank
//@Size(min = 6, max = 15, message = "Password length must 6 and 15 symbol")
//@Pattern(regexp = "[a-zA-Z0-9]", message = "Password must be alphanumeric")
@Retention(RetentionPolicy.RUNTIME)
@Target({PARAMETER, FIELD})
@Constraint(validatedBy = {})
public @interface StrongPassword {
    String message() default "Wrong password format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
