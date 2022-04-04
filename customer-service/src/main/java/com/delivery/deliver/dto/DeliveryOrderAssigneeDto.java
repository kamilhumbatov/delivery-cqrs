package com.delivery.deliver.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeliveryOrderAssigneeDto {

    @NotBlank
    @ApiModelProperty(value = "Courier username", example = "araz")
    private String assignee;
}
