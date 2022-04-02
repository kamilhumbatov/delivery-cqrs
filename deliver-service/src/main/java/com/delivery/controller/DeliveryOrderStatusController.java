package com.delivery.controller;

import com.delivery.service.DeliveryOrderStatusService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("delivery-orders/status")
@RequiredArgsConstructor
public class DeliveryOrderStatusController {

    private final DeliveryOrderStatusService service;

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/pickup/{id}")
    public CompletableFuture<String> pickupOrder(@PathVariable Long id) {
        return service.changeOrderStatusToPickUp(id);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/deliver/{id}")
    public CompletableFuture<String> deliveryOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivery(id);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/delivered/{id}")
    public CompletableFuture<String> deliveredOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivered(id);
    }

    @Secured({RoleName.ROLE_CUSTOMER, RoleName.ROLE_COURIER, RoleName.ROLE_ADMIN})
    @PutMapping("/cancel/{id}")
    public CompletableFuture<String> cancelOrder(@PathVariable Long id) {
        return service.changeOrderStatusToCancel(id);
    }
}
