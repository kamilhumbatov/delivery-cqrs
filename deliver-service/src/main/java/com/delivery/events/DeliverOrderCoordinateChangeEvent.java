package com.delivery.events;

import com.delivery.user.domain.DeliveryOrderCoordinate;
import lombok.Data;

@Data
public class DeliverOrderCoordinateChangeEvent {
    private DeliveryOrderCoordinate coordinate;
}
