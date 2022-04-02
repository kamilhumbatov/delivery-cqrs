package com.delivery.deliver.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeliveryOrderAssigneeDto {

    @NotNull
    private String id;

    @NotBlank
    private String assignee;
}
