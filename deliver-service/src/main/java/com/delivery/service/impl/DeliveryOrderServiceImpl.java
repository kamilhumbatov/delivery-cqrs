package com.delivery.service.impl;

import com.delivery.exception.DeliveryOrderNotFoundException;
import com.delivery.service.DeliveryOrderService;
import com.delivery.user.domain.DeliveryOrder;
import com.delivery.user.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository repository;

    @Override
    public DeliveryOrder findById(long id) {
        return repository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
    }

    @Override
    public List<DeliveryOrder> findAllOrdersByOwner(String username) {
        return repository.findAllByOwner(username);
    }

    @Override
    public List<DeliveryOrder> findAllOrdersByAssignee(String username) {
        return repository.findAllByAssignee(username);
    }

    @Override
    public DeliveryOrder save(DeliveryOrder deliveryOrder) {
        return repository.save(deliveryOrder);
    }
}
