package com.delivery.service;

import com.delivery.domain.DeliveryOrderCoordinate;

public interface DeliveryOrderDestinationService {
    void updateLastCoordinate(DeliveryOrderCoordinate orderCoordinate);
}
