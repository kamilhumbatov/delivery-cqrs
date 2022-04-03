package com.delivery.deliver.service;

import com.delivery.deliver.domain.DeliveryOrderCoordinate;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;

public interface DeliveryOrderDestinationService {
    void updateLastCoordinate(DeliveryOrderCoordinate orderCoordinate);

    String changeDestination(String id, DeliveryOrderDestinationDto destinationDto);
}
