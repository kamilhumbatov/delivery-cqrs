package com.delivery.controller;

import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("owner/{owner}")
    public List<DeliveryOrderDto> findAllOrdersByOwner(@PathVariable String owner) {
        return service.findAllOrdersByOwner(owner);
    }

    @GetMapping("assignee/{assignee}")
    public List<DeliveryOrderDto> findAllOrdersByAssignee(@PathVariable String assignee) {
        return service.findAllOrdersByAssignee(assignee);
    }

    @PostMapping
    public DeliveryOrderDto create(@RequestBody DeliveryOrderCreateDto createDto) {
        return service.createOrder(createDto);
    }

    @PutMapping("assignee")
    public DeliveryOrderDto assigneeOrderToCourier(@RequestBody DeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(assigneeDto);
    }

    @PatchMapping
    public DeliveryOrderDto cancelOrder(@PathVariable Long id) {
        return service.cancelOrder(id);
    }
}
