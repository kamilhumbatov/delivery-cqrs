package com.delivery.deliver.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private String id;

    private final double accountBalance;

    private final String currency;
}
