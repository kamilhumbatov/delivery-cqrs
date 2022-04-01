package com.delivery.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.service.DeliveryOrderCommandService;
import com.delivery.service.DeliveryOrderService;
import com.delivery.service.mapper.DeliveryOrderCreateMapper;
import com.delivery.service.mapper.DeliveryOrderDestinationCreateMapper;
import com.delivery.service.mapper.DeliveryOrderMapper;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.domain.DeliveryOrderDestination;
import com.delivery.user.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository repository;
    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderCreateMapper createMapper;
    private final DeliveryOrderDestinationCreateMapper destinationCreateMapper;
    private final CurrentUserService currentUserService;

    @Override
    public DeliveryOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                String.format("Invalid DeliveryOrder id %s", id)));
    }
}
