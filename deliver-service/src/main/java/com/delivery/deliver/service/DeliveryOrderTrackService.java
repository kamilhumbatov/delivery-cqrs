package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;

public interface DeliveryOrderTrackService {

    String changeCoordinate(String id, DeliveryOrderDestinationDto destinationDto);
}
