package com.delivery.deliver.events;

import com.delivery.deliver.enums.DeliveryOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliverOrderActivatedEvent {

    private String id;
    private DeliveryOrderStatus status;

}