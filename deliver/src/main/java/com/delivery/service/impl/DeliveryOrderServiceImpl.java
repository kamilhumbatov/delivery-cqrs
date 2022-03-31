package com.delivery.service.impl;

import com.delivery.domain.DeliveryOrder;
import com.delivery.domain.DeliveryOrderDestination;
import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.repository.DeliveryOrderRepository;
import com.delivery.service.DeliveryOrderService;
import com.delivery.service.mapper.DeliveryOrderCreateMapper;
import com.delivery.service.mapper.DeliveryOrderDestinationCreateMapper;
import com.delivery.service.mapper.DeliveryOrderMapper;
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

    @Override
    public DeliveryOrderDto getOrder(Long id) {
        DeliveryOrder deliveryOrder = findById(id);
        return mapper.toDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto createOrder(DeliveryOrderCreateDto createDto) {
        DeliveryOrder deliveryOrder = createMapper.toDbo(createDto);
        deliveryOrder.setStatus(DeliveryOrderStatus.CREATED);

        DeliveryOrderDestination deliveryOrderDestination = destinationCreateMapper.toDbo(createDto);
        deliveryOrderDestination.setOrder(deliveryOrder);

        return mapper.toDto(repository.save(deliveryOrder));
    }

    @Override
    public DeliveryOrderDto cancelOrder(Long id) {
        DeliveryOrder deliveryOrder = findById(id);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.DELIVERED) == 0) {
            throw new IllegalArgumentException("Can not cancelled");
        }
        deliveryOrder.setStatus(DeliveryOrderStatus.CANCELED);
        return mapper.toDto(repository.save(deliveryOrder));
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToPickUp(Long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivery(Long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivered(Long id) {
        return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
    }

    @Override
    public DeliveryOrderDto assigneeOrderToCourier(DeliveryOrderAssigneeDto assigneeDto) {
        DeliveryOrder deliveryOrder = findById(assigneeDto.getId());
        deliveryOrder.setAssignee(assigneeDto.getAssignee());
        return mapper.toDto(repository.save(deliveryOrder));
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByOwner(String assignee) {
        return repository.findAllOrdersByOwner(assignee)
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByAssignee(String assignee) {
        return repository.findAllByAssignee(assignee)
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    private DeliveryOrderDto changeOrderStatus(Long id, DeliveryOrderStatus status) {
        DeliveryOrder deliveryOrder = findById(id);
        deliveryOrder.setStatus(status);
        return mapper.toDto(repository.save(deliveryOrder));
    }

    private DeliveryOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                String.format("Invalid DeliveryOrder id %s", id)));
    }
}
