package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.service.handlers.DeliveryOrderQueryHandlerService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderQueryHandlerService service;

    @GetMapping("/{id}")
    public DeliveryOrderDto findById(@PathVariable String id) {
        return service.getOrder(id);
    }

    @Secured(RoleName.ROLE_CUSTOMER)
    @GetMapping("/owner")
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        return service.findAllOrdersByOwner();
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/assignee")
    public List<DeliveryOrderDto> findAllOrdersByAssignee() {
        return service.findAllOrdersByAssignee();
    }
}
