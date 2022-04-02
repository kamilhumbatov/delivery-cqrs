package com.delivery.deliver.service;

import com.delivery.deliver.domain.DeliveryOrderCoordinate;

public interface DeliveryOrderDestinationService {
    void updateLastCoordinate(DeliveryOrderCoordinate orderCoordinate);
}
