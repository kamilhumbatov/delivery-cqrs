package com.delivery.service;

import com.delivery.events.DeliverOrderCoordinateChangedEvent;
import com.delivery.user.domain.DeliveryOrderCoordinate;

public interface DeliveryOrderCoordinateService {
    DeliveryOrderCoordinate addCoordinate(DeliverOrderCoordinateChangedEvent coordinateChangedEvent);
}
