package com.delivery.courier.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreditMoneyCommand  {

    @TargetAggregateIdentifier
    private String id;

    public final double creditAmount;

    public final String currency;

}
