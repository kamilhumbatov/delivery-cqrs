package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
