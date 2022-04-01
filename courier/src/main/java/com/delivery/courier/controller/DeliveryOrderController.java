package com.delivery.courier.controller;

import com.delivery.courier.dto.DeliveryOrderDto;
import com.delivery.courier.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderService service;

    @PutMapping("/status/pickup/{id}")
    public DeliveryOrderDto changeOrderStatusToPickUp(Long id) {
        return service.changeOrderStatusToPickUp(id);
    }

    @PutMapping("/status/delivery/{id}")
    public DeliveryOrderDto changeOrderStatusToDelivery(Long id) {
        return service.changeOrderStatusToDelivery(id);
    }

    @PutMapping("/status/delivered/{id}")
    public DeliveryOrderDto changeOrderStatusToDelivered(Long id) {
        return service.changeOrderStatusToDelivered(id);
    }
}
