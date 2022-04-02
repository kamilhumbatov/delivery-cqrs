package com.delivery.courier.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedEvent  {

    private String id;

    private double accountBalance;

    private String currency;

}
