package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.ResponseDto;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import com.delivery.security.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery-orders/destination")
@RequiredArgsConstructor
public class DeliveryOrderDestinationController {

    private final DeliveryOrderDestinationService service;

    @Secured(RoleName.ROLE_CUSTOMER)
    @PutMapping("/{id}")
    public ResponseDto changeDestination(
            @PathVariable String id,
            @RequestBody DeliveryOrderDestinationDto destinationDto) {
        return new ResponseDto(service.changeDestination(id, destinationDto));
    }
}
