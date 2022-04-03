package com.delivery.deliver.commands;

import com.delivery.deliver.enums.DeliveryOrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ChangeStatusCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotBlank
    private DeliveryOrderStatus status;
}
