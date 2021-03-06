package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.service.handlers.DeliveryOrderQueryHandlerService;
import com.delivery.security.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderQueryHandlerService service;

    @Secured(RoleName.ROLE_ADMIN)
    @GetMapping("/admin/{id}")
    public DeliveryOrderDto findByIdAdmin(@PathVariable String id) {
        return service.getOrder(id, RoleName.ROLE_ADMIN);
    }

    @Secured(RoleName.ROLE_CUSTOMER)
    @GetMapping("/customer/{id}")
    public DeliveryOrderDto findByIdCustomer(@PathVariable String id) {
        return service.getOrder(id, RoleName.ROLE_CUSTOMER);
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/courier/{id}")
    public DeliveryOrderDto findByIdCourier(@PathVariable String id) {
        return service.getOrder(id, RoleName.ROLE_COURIER);
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
