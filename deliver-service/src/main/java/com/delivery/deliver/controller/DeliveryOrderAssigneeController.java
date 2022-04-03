package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.service.DeliveryOrderAssigneeService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderAssigneeController {

    private final DeliveryOrderAssigneeService service;

    @Secured(RoleName.ROLE_ADMIN)
    @PutMapping("assignee/{id}")
    public String assigneeOrderToCourier(
            @PathVariable String id,
            @RequestBody DeliveryOrderAssigneeDto assigneeDto) {
        return service.assigneeOrderToCourier(id, assigneeDto.getAssignee());
    }
}
