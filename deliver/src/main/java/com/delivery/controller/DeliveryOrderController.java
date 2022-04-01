package com.delivery.controller;

import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.service.DeliveryOrderService;
import com.delivery.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderService service;

    @GetMapping("/{id}")
    public DeliveryOrderDto getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @Secured(Roles.ROLE_USER)
    @GetMapping("/owner")
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        return service.findAllOrdersByOwner();
    }

    @Secured(Roles.ROLE_COURIER)
    @GetMapping("/assignee")
    public List<DeliveryOrderDto> findAllOrdersByAssignee() {
        return service.findAllOrdersByAssignee();
    }

    @Secured(Roles.ROLE_USER)
    @PostMapping
    public DeliveryOrderDto create(@RequestBody DeliveryOrderCreateDto createDto) {
        return service.createOrder(createDto);
    }

    @Secured(Roles.ROLE_ADMIN)
    @PutMapping("assignee")
    public DeliveryOrderDto assigneeOrderToCourier(@RequestBody DeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(assigneeDto);
    }

    @Secured(Roles.ROLE_USER)
    @PutMapping("destination")
    public DeliveryOrderDto changeDestination(@RequestBody DeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(assigneeDto);
    }

    @Secured(Roles.ROLE_USER)
    @PutMapping("/cancel")
    public DeliveryOrderDto cancelOrder(@PathVariable Long id) {
        return service.cancelOrder(id);
    }
}
