package com.delivery.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.service.DeliveryOrderService;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.domain.DeliveryOrderDestination;
import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.user.repository.DeliveryOrderRepository;
import com.delivery.service.DeliveryOrderCommandService;
import com.delivery.service.mapper.DeliveryOrderCreateMapper;
import com.delivery.service.mapper.DeliveryOrderDestinationCreateMapper;
import com.delivery.service.mapper.DeliveryOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryOrderCommandServiceImpl implements DeliveryOrderCommandService {

    private final DeliveryOrderService orderService;
    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderCreateMapper createMapper;
    private final DeliveryOrderDestinationCreateMapper destinationCreateMapper;
    private final CurrentUserService currentUserService;

    @Override
    public DeliveryOrderDto getOrder(Long id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        return mapper.toDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto createOrder(DeliveryOrderCreateDto createDto) {
        DeliveryOrder deliveryOrder = createMapper.toDbo(createDto);
        deliveryOrder.setStatus(DeliveryOrderStatus.CREATED);
        deliveryOrder.setOwner(currentUserService.getCurrentUser());

        DeliveryOrderDestination deliveryOrderDestination = destinationCreateMapper.toDbo(createDto);
        deliveryOrderDestination.setOrder(deliveryOrder);

        return mapper.toDto(repository.save(deliveryOrder));
    }

    @Override
    public DeliveryOrderDto changeDestination(Long id, DeliveryOrderDestinationDto destinationDto) {
        return null;
    }

    @Override
    public DeliveryOrderDto assigneeOrderToCourier(DeliveryOrderAssigneeDto assigneeDto) {
        DeliveryOrder deliveryOrder = findById(assigneeDto.getId());
        deliveryOrder.setAssignee(assigneeDto.getAssignee());
        return mapper.toDto(repository.save(deliveryOrder));
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        return repository.findAllOrdersByOwner(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByAssignee() {
        return repository.findAllByAssignee(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
