package com.delivery.deliver.controller;

import com.delivery.deliver.service.DeliveryOrderStatusService;
import com.delivery.util.RoleName;
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

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/pickup/{id}")
    public String pickupOrder(@PathVariable String id) {
        return service.changeOrderStatusToPickUp(id);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/deliver/{id}")
    public String deliveryOrder(@PathVariable String id) {
        return service.changeOrderStatusToDelivery(id);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/delivered/{id}")
    public String deliveredOrder(@PathVariable String id) {
        return service.changeOrderStatusToDelivered(id);
    }

    @Secured({RoleName.ROLE_CUSTOMER, RoleName.ROLE_COURIER, RoleName.ROLE_ADMIN})
    @PutMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable String id) {
        return service.changeOrderStatusToCancel(id);
    }
}
