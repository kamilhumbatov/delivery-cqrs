package com.delivery.deliver.controller;

import com.delivery.deliver.dto.ResponseDto;
import com.delivery.deliver.service.DeliveryOrderStatusService;
import com.delivery.security.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery-orders/status")
@RequiredArgsConstructor
public class DeliveryOrderStatusController {

    private final DeliveryOrderStatusService service;

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/pickup/{id}")
    public ResponseDto pickupOrder(@PathVariable String id) {
        return new ResponseDto(service.changeOrderStatusToPickUp(id));
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/deliver/{id}")
    public ResponseDto deliveryOrder(@PathVariable String id) {
        return new ResponseDto(service.changeOrderStatusToDelivery(id));
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/delivered/{id}")
    public ResponseDto deliveredOrder(@PathVariable String id) {
        return new ResponseDto(service.changeOrderStatusToDelivered(id));
    }

    @Secured({RoleName.ROLE_CUSTOMER, RoleName.ROLE_COURIER, RoleName.ROLE_ADMIN})
    @PutMapping("/cancel/{id}")
    public ResponseDto cancelOrder(@PathVariable String id) {
        return new ResponseDto(service.changeOrderStatusToCancel(id));
    }
}
