package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderCreateDto;

import java.util.concurrent.CompletableFuture;

public interface DeliveryOrderCreateService {

    String createOrder(DeliveryOrderCreateDto createDto);
}
