package com.delivery.deliver.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreditMoneyCommand  {

    @TargetAggregateIdentifier
    private String id;

    private  double creditAmount;

    private  String currency;

}
