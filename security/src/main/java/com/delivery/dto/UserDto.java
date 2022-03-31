package com.delivery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String ip;

    private String browser;

    private String device;

    private String os;
}
