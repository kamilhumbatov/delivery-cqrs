package com.delivery.courier.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DebitMoneyCommand {

    @TargetAggregateIdentifier
    private String id;

    public final double debitAmount;

    public final String currency;

}
