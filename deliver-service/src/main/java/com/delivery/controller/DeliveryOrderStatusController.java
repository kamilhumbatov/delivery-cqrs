package com.delivery.controller;

import com.delivery.dto.DeliveryOrderDto;
import com.delivery.service.DeliveryOrderStatusService;
import com.delivery.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery-orders/status")
@RequiredArgsConstructor
public class DeliveryOrderStatusController {

    private final DeliveryOrderStatusService service;

    @Secured(Roles.ROLE_USER)
    @GetMapping("/pickup/{id}")
    public DeliveryOrderDto pickupOrder(@PathVariable Long id) {
        return service.changeOrderStatusToPickUp(id);
    }

    @Secured(Roles.ROLE_USER)
    @GetMapping("/deliver/{id}")
    public DeliveryOrderDto deliveryOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivery(id);
    }

    @Secured(Roles.ROLE_USER)
    @GetMapping("/delivered/{id}")
    public DeliveryOrderDto deliveredOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivered(id);
    }

    @Secured({Roles.ROLE_USER, Roles.ROLE_ADMIN})
    @PutMapping("/cancel/{id}")
    public DeliveryOrderDto cancelOrder(@PathVariable Long id) {
        return service.changeOrderStatusToCancel(id);
    }
}
