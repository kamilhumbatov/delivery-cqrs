package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.service.DeliveryOrderStatusService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("delivery-orders/status")
@RequiredArgsConstructor
public class DeliveryOrderStatusController {

    private final DeliveryOrderStatusService service;

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/pickup/{id}")
    public DeliveryOrderDto pickupOrder(@PathVariable Long id) {
        return service.changeOrderStatusToPickUp(id);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/deliver/{id}")
    public DeliveryOrderDto deliveryOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivery(id);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/delivered/{id}")
    public DeliveryOrderDto deliveredOrder(@PathVariable Long id) {
        return service.changeOrderStatusToDelivered(id);
    }

    @Secured({RoleName.ROLE_CUSTOMER, RoleName.ROLE_COURIER, RoleName.ROLE_ADMIN})
    @PutMapping("/cancel/{id}")
    public DeliveryOrderDto cancelOrder(@PathVariable Long id) {
        return service.changeOrderStatusToCancel(id);
    }
}
