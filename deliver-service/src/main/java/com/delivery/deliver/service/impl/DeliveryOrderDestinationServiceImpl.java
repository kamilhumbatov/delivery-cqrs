package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrderCoordinate;
import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.repository.DeliveryOrderDestinationRepository;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderDestinationServiceImpl implements DeliveryOrderDestinationService {

    private final DeliveryOrderDestinationRepository deliveryOrderDestinationRepository;

    public void updateLastCoordinate(DeliveryOrderCoordinate orderCoordinate) {
        DeliveryOrderDestination deliveryOrderDestination = orderCoordinate.getOrder().getDestination();
        deliveryOrderDestination.setLastLocation(orderCoordinate);
        deliveryOrderDestinationRepository.save(deliveryOrderDestination);
    }
}
