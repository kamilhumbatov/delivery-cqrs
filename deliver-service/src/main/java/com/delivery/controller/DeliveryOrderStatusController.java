package com.delivery.controller;

import com.delivery.service.DeliveryOrderStatusCommandService;
import com.delivery.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("delivery-orders/status")
@RequiredArgsConstructor
public class DeliveryOrderStatusController {

    private final DeliveryOrderStatusCommandService service;

    @Secured(Roles.ROLE_USER)
    @GetMapping("/pickup/{id}")
    public CompletableFuture<String> pickupOrder(@PathVariable Long id) {
        return service.changeOrderStatusToPickUp(id);
    }

    @Secured(Roles.ROLE_USER)
    @GetMapping("/deliver/{id}")
    public CompletableFuture<String> deliveryOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivery(id);
    }

    @Secured(Roles.ROLE_USER)
    @GetMapping("/delivered/{id}")
    public CompletableFuture<String> deliveredOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivered(id);
    }

    @Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
    @PutMapping("/cancel/{id}")
    public CompletableFuture<String> cancelOrder(@PathVariable Long id) {
        return service.changeOrderStatusToCancel(id);
    }
}
