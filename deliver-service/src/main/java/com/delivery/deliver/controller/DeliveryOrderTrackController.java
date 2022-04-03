package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("delivery-orders/track")
@RequiredArgsConstructor
public class DeliveryOrderTrackController {

    private final DeliveryOrderTrackService service;

    @Secured(RoleName.ROLE_COURIER)
    @PostMapping("/{id}")
    public String changeCoordinate(
            @PathVariable String id,
            @RequestBody DeliveryOrderDestinationDto coordinateDto) {
        return service.changeCoordinate(id, coordinateDto);
    }
}
