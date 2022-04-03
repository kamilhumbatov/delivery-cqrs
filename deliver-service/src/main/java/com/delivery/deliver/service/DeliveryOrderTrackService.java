package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;

import java.util.concurrent.CompletableFuture;

public interface DeliveryOrderTrackService {

    String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto);
}
