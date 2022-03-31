package com.delivery.service.impl;

import com.delivery.domain.ParcelDeliveryOrder;
import com.delivery.dto.ParcelDeliveryOrderAssigneeDto;
import com.delivery.dto.ParcelDeliveryOrderDto;
import com.delivery.enums.ParcelDeliveryOrderStatus;
import com.delivery.repository.ParcelDeliveryOrderRepository;
import com.delivery.service.ParcelDeliveryOrderService;
import com.delivery.service.mapper.ParcelDeliveryOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParcelDeliveryOrderServiceImpl implements ParcelDeliveryOrderService {

    private final ParcelDeliveryOrderRepository repository;
    private final ParcelDeliveryOrderMapper mapper;

    @Override
    public ParcelDeliveryOrderDto getOrder(Long id) {
        ParcelDeliveryOrder parcelOrder = findById(id);
        return mapper.toDto(parcelOrder);
    }

    @Override
    public ParcelDeliveryOrderDto createOrder(ParcelDeliveryOrderDto createDto) {
        ParcelDeliveryOrder parcelDeliveryOrder = mapper.toDbo(createDto);
        parcelDeliveryOrder.setStatus(ParcelDeliveryOrderStatus.CREATED);
        return mapper.toDto(repository.save(parcelDeliveryOrder));
    }

    @Override
    public ParcelDeliveryOrderDto cancelOrder(Long id) {
        ParcelDeliveryOrder parcelDeliveryOrder = findById(id);
        if (parcelDeliveryOrder.getStatus().compareTo(ParcelDeliveryOrderStatus.DELIVERED) == 0) {
            throw new IllegalArgumentException("Can not cancelled");
        }
        parcelDeliveryOrder.setStatus(ParcelDeliveryOrderStatus.CANCELED);
        return mapper.toDto(repository.save(parcelDeliveryOrder));
    }

    @Override
    public ParcelDeliveryOrderDto changeOrderStatusToPickUp(Long id) {
        return changeOrderStatus(id, ParcelDeliveryOrderStatus.PICKUP);
    }

    @Override
    public ParcelDeliveryOrderDto changeOrderStatusToDelivery(Long id) {
        return changeOrderStatus(id, ParcelDeliveryOrderStatus.DELIVERY);
    }

    @Override
    public ParcelDeliveryOrderDto changeOrderStatusToDelivered(Long id) {
        return changeOrderStatus(id, ParcelDeliveryOrderStatus.DELIVERED);
    }

    @Override
    public ParcelDeliveryOrderDto assigneeOrderToCourier(ParcelDeliveryOrderAssigneeDto assigneeDto) {
        ParcelDeliveryOrder parcelDeliveryOrder = findById(assigneeDto.getId());
        parcelDeliveryOrder.setAssignee(assigneeDto.getAssignee());
        return mapper.toDto(repository.save(parcelDeliveryOrder));
    }

    @Override
    public List<ParcelDeliveryOrderDto> findAllOrdersByOwner(String assignee) {
        return repository.findAllOrdersByOwner(assignee)
                .stream()
                .map(ParcelDeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ParcelDeliveryOrderDto> findAllOrdersByAssignee(String assignee) {
        return repository.findAllByAssignee(assignee)
                .stream()
                .map(ParcelDeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    private ParcelDeliveryOrderDto changeOrderStatus(Long id, ParcelDeliveryOrderStatus status) {
        ParcelDeliveryOrder parcelDeliveryOrder = findById(id);
        parcelDeliveryOrder.setStatus(status);
        return mapper.toDto(repository.save(parcelDeliveryOrder));
    }

    private ParcelDeliveryOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                String.format("Invalid ParcelDeliveryOrder id %s", id)));
    }
}
