package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.exception.DeliveryOrderNotFoundException;
import com.delivery.deliver.repository.DeliveryOrderRepository;
import com.delivery.deliver.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository repository;

    @Override
    public DeliveryOrder findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
    }

    @Override
    public DeliveryOrder findByIdAndOwner(String id, String owner) {
        return repository.findByIdAndOwner(id, owner).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
    }

    @Override
    public DeliveryOrder findByIdAndAssignee(String id, String assignee) {
        return repository.findByIdAndAssignee(id, assignee).orElseThrow(() -> new DeliveryOrderNotFoundException(id));
    }

    @Override
    public List<DeliveryOrder> findAllOrdersByOwner(String owner) {
        return repository.findAllByOwner(owner);
    }

    @Override
    public List<DeliveryOrder> findAllOrdersByAssignee(String owner) {
        return repository.findAllByAssignee(owner);
    }

    @Override
    public DeliveryOrder save(DeliveryOrder deliveryOrder) {
        return repository.save(deliveryOrder);
    }
}
