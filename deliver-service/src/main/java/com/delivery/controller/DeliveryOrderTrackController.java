package com.delivery.controller;

import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.service.DeliveryOrderTrackService;
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
    public CompletableFuture<String> changeCoordinate(
            @PathVariable Long id,
            @RequestBody DeliveryOrderDestinationDto coordinateDto) {
        return service.changeCoordinate(id, coordinateDto);
    }
}
