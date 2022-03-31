package com.delivery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeliveryOrderDestinationCreateDto {

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;
}
