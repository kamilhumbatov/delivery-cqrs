package com.delivery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeliveryOrderDestinationDto {

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;
}
