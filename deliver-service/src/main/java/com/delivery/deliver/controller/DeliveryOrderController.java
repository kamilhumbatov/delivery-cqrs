package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.service.DeliveryOrderCommandService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderCommandService service;

    @GetMapping("/{id}")
    public DeliveryOrderDto getOrder(@PathVariable String id) {
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

    @Secured(RoleName.ROLE_CUSTOMER)
    @PostMapping
    public DeliveryOrderDto create(@RequestBody DeliveryOrderCreateDto createDto) {
        return service.createOrder(createDto);
    }

    @Secured(RoleName.ROLE_ADMIN)
    @PutMapping("assignee")
    public DeliveryOrderDto assigneeOrderToCourier(@RequestBody DeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(assigneeDto);
    }

    @Secured(RoleName.ROLE_CUSTOMER)
    @PutMapping("destination")
    public DeliveryOrderDto changeDestination(@RequestBody DeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(assigneeDto);
    }
}
