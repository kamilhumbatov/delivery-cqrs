package com.delivery.controller;

import com.delivery.dto.ParcelDeliveryOrderAssigneeDto;
import com.delivery.dto.ParcelDeliveryOrderDto;
import com.delivery.service.ParcelDeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parcel-delivery-orders")
@RequiredArgsConstructor
public class ParcelDeliveryOrderController {

    private final ParcelDeliveryOrderService service;

    @GetMapping("/{id}")
    public ParcelDeliveryOrderDto getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @GetMapping("assignee/{assignee}")
    public List<ParcelDeliveryOrderDto> findAllOrdersByAssignee(@PathVariable String assignee) {
        return service.findAllOrdersByAssignee(assignee);
    }

    @PutMapping("assignee")
    public ParcelDeliveryOrderDto assigneeOrderToCourier(@RequestBody ParcelDeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(assigneeDto);
    }
}
