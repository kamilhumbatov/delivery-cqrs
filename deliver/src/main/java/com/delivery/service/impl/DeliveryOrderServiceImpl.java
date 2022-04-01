package com.delivery.service.impl;

import com.delivery.CurrentUserService;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.domain.DeliveryOrderDestination;
import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.user.repository.DeliveryOrderRepository;
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
    private final CurrentUserService currentUserService;

    @Override
    public DeliveryOrderDto getOrder(Long id) {
        DeliveryOrder deliveryOrder = findById(id);
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
