package com.delivery.deliver.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliverOrderCreatedEvent {

    private String orderId;
    private String owner;
    private String latitude;
    private String longitude;
}
