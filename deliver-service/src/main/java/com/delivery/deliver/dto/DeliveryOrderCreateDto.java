package com.delivery.deliver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeliveryOrderCreateDto {

    @NotBlank
    @ApiModelProperty(value = "Latitude coordinate", example = "41.123")
    private String latitude;

    @NotBlank
    @ApiModelProperty(value = "Longitude coordinate", example = "42.345")
    private String longitude;
}
