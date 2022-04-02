package com.delivery.courier.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDebitedEvent {
    private String id;

    private  double debitAmount;

    private  String currency;
}
