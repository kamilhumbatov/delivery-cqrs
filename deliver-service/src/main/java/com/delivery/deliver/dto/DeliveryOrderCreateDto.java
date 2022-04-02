package com.delivery.deliver.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeliveryOrderCreateDto {

    @NotBlank
    private String owner;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;
}
