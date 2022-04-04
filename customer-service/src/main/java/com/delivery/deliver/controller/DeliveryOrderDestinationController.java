package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery-orders/destination")
@RequiredArgsConstructor
public class DeliveryOrderDestinationController {

    private final DeliveryOrderDestinationService service;

    @Secured(RoleName.ROLE_CUSTOMER)
    @PutMapping("/{id}")
    public String changeDestination(
            @PathVariable String id,
            @RequestBody DeliveryOrderDestinationDto destinationDto) {
        return service.changeDestination(id, destinationDto);
    }
}
