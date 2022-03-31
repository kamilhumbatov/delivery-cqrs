package com.delivery.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ParcelDeliveryOrderAssigneeDto {

    @NotNull
    private Long id;

    @NotBlank
    private String assignee;
}
