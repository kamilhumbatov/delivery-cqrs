package com.delivery.service.impl;

import com.delivery.events.DeliverOrderCoordinateChangedEvent;
import com.delivery.service.DeliveryOrderCoordinateService;
import com.delivery.user.domain.DeliveryOrderCoordinate;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderCoordinateServiceImpl implements DeliveryOrderCoordinateService {

    @EventHandler
    public DeliveryOrderCoordinate addCoordinate(DeliverOrderCoordinateChangedEvent coordinateChangedEvent) {
        return null;
    }
}
