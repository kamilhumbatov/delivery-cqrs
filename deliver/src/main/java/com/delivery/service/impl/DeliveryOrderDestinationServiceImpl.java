package com.delivery.service.impl;

import com.delivery.repository.DeliveryOrderDestinationRepository;
import com.delivery.service.DeliveryOrderDestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderDestinationServiceImpl implements DeliveryOrderDestinationService {

    private final DeliveryOrderDestinationRepository deliveryOrderDestinationRepository;
}
