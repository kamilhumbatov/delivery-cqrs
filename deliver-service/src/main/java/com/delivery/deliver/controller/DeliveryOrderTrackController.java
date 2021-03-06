package com.delivery.deliver.controller;

import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.ResponseDto;
import com.delivery.deliver.service.DeliveryOrderTrackService;
import com.delivery.security.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delivery-orders/track")
@RequiredArgsConstructor
public class DeliveryOrderTrackController {

    private final DeliveryOrderTrackService service;

    @Secured(RoleName.ROLE_COURIER)
    @PostMapping("/{id}")
    public ResponseDto changeCoordinate(
            @PathVariable String id,
            @RequestBody DeliveryOrderDestinationDto coordinateDto) {
        return new ResponseDto(service.changeCoordinate(id, coordinateDto));
    }
}
