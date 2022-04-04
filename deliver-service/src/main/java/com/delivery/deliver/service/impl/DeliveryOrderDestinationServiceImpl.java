package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderDestination;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.exception.DeliveryOrderStatusException;
import com.delivery.deliver.repository.DeliveryOrderDestinationRepository;
import com.delivery.deliver.service.DeliveryOrderDestinationService;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.commands.OrderCommandService;
import com.delivery.deliver.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryOrderDestinationServiceImpl implements DeliveryOrderDestinationService {

    private final SecurityUtil securityUtil;
    private final DeliveryOrderService orderService;
    private final OrderCommandService orderCommand;
    private final DeliveryOrderDestinationRepository repository;

    @Override
    public String changeDestination(String id, DeliveryOrderDestinationDto destinationDto) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserOwner(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.PENDING) == 0) {
            return orderCommand.changeDestination(id, destinationDto);
        }
        throw new DeliveryOrderStatusException("Order status is not Pending");
    }

    @Override
    public void updateDestination(DeliveryOrderDestination deliveryOrderDestination) {
        repository.save(deliveryOrderDestination);
    }
}
