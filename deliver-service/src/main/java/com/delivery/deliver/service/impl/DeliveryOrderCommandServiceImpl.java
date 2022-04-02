package com.delivery.deliver.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderAssigneeDto;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.service.DeliveryOrderCommandService;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.mapper.DeliveryOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryOrderCommandServiceImpl implements DeliveryOrderCommandService {

    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;

    @Override
    public DeliveryOrderDto getOrder(String id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        return mapper.toDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto changeDestination(String id, DeliveryOrderDestinationDto destinationDto) {
        return null;
    }

    @Override
    public DeliveryOrderDto assigneeOrderToCourier(DeliveryOrderAssigneeDto assigneeDto) {
        DeliveryOrder deliveryOrder = orderService.findById(assigneeDto.getId());
        deliveryOrder.setAssignee(assigneeDto.getAssignee());
        return mapper.toDto(orderService.save(deliveryOrder));
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        return orderService.findAllOrdersByOwner(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByAssignee() {
        return orderService.findAllOrdersByAssignee(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
