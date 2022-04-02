package com.delivery.events;

import com.delivery.user.domain.DeliveryOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliverOrderStatusChangedEvent {
    private DeliveryOrder order;
}
