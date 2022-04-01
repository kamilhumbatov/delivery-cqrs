package com.delivery.service;

import com.delivery.dto.DeliveryOrderAssigneeDto;
import com.delivery.dto.DeliveryOrderCreateDto;
import com.delivery.dto.DeliveryOrderDestinationDto;
import com.delivery.dto.DeliveryOrderDto;
import com.delivery.user.domain.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderService {
    DeliveryOrder findById(long id);
}
