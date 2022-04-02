package com.delivery.service.impl;

import com.delivery.domain.DeliveryOrderCoordinate;
import com.delivery.domain.DeliveryOrderDestination;
import com.delivery.repository.DeliveryOrderDestinationRepository;
import com.delivery.service.DeliveryOrderDestinationService;
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
