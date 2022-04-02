package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;

import java.util.concurrent.CompletableFuture;

public interface DeliveryOrderTrackService {

    CompletableFuture<String> changeCoordinate(long id, DeliveryOrderDestinationDto destinationDto);
}
