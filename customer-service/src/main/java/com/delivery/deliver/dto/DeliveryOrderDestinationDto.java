package com.delivery.deliver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryOrderDestinationDto {

    @ApiModelProperty(value = "Latitude coordinate", example = "41.123")
    private String latitude;

    @NotBlank
    @ApiModelProperty(value = "Longitude coordinate", example = "42.345")
    private String longitude;
}
