package com.delivery.deliver.service;

import com.delivery.deliver.dto.DeliveryOrderCreateDto;

import java.util.concurrent.CompletableFuture;

public interface DeliveryOrderCreateService {

    CompletableFuture<String> createOrder(DeliveryOrderCreateDto createDto);
}
