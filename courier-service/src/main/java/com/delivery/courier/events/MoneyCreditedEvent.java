package com.delivery.courier.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoneyCreditedEvent {

    private String id;

    private  double creditAmount;

    private  String currency;
}
