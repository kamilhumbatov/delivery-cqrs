package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderCreateDto;
import com.delivery.deliver.service.commands.OrderCommandService;
import com.delivery.security.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderCreateController {

    private final OrderCommandService orderCommandService;

    @Secured(RoleName.ROLE_CUSTOMER)
    @PostMapping
    public String create(@RequestBody DeliveryOrderCreateDto createDto) {
        return orderCommandService.createOrder(createDto);
    }
}
