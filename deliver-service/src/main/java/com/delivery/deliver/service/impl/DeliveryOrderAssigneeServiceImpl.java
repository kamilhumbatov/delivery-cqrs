package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.service.DeliveryOrderAssigneeService;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.commands.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderAssigneeServiceImpl implements DeliveryOrderAssigneeService {

    private final DeliveryOrderService orderService;
    private final OrderCommandService orderCommandService;

    @Override
    public String assigneeOrderToCourier(String id, String assignee) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.PENDING) == 0) {
            return orderCommandService.assigneeOrder(id, assignee);
        }
        throw new DeliveryOrderStatusException("Order status is not Pending!");
    }
}
