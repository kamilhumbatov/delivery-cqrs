package com.delivery.service;

import java.util.concurrent.CompletableFuture;

public interface DeliveryOrderStatusCommandService {

    CompletableFuture<String> changeOrderStatusToPickUp(long id);

    CompletableFuture<String> changeOrderStatusToDelivery(long id);

    CompletableFuture<String> changeOrderStatusToDelivered(long id);

    CompletableFuture<String> changeOrderStatusToCancel(long id);
}
