package com.delivery.service;

import com.delivery.dto.DeliveryOrderDestinationDto;

import java.util.concurrent.CompletableFuture;

public interface DeliveryOrderTrackService {

    CompletableFuture<String> changeCoordinate(long id, DeliveryOrderDestinationDto destinationDto);
}
