package com.delivery.deliver.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AssigneeOrderCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotBlank
    private String assignee;
}
