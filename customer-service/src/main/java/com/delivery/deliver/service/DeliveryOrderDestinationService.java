package com.delivery.deliver.service;

import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;

public interface DeliveryOrderDestinationService {

    String changeDestination(String id, DeliveryOrderDestinationDto destinationDto);

    void updateDestination(DeliveryOrderDestination deliveryOrderDestination);
}
