package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.service.DeliveryOrderCommandService;
import com.delivery.deliver.service.DeliveryOrderCreateService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderCommandService service;
    private final DeliveryOrderCreateService createService;

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

    @GetMapping("/{accountNumber}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber){
        return service.listEventsForAccount(accountNumber);
    }

    @Secured(RoleName.ROLE_CUSTOMER)
    @PostMapping
    public String create(@RequestBody DeliveryOrderCreateDto createDto) {
        return createService.createOrder(createDto);
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
