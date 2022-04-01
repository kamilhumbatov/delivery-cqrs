package com.delivery.courier.service.impl;

import com.delivery.courier.dto.DeliveryOrderDto;
import com.delivery.courier.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    @Override
    public DeliveryOrderDto changeOrderStatusToPickUp(Long id) {
        //return changeOrderStatus(id, DeliveryOrderStatus.PICKUP);
        return null;
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivery(Long id) {
        //return changeOrderStatus(id, DeliveryOrderStatus.DELIVERY);
        return null;
    }

    @Override
    public DeliveryOrderDto changeOrderStatusToDelivered(Long id) {
        //return changeOrderStatus(id, DeliveryOrderStatus.DELIVERED);
        return null;
    }
}
