package com.delivery.deliver.service.impl;

import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.domain.DeliveryOrderCoordinate;
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
    private final DeliveryOrderDestinationRepository deliveryOrderDestinationRepository;

    public void updateLastCoordinate(DeliveryOrderCoordinate orderCoordinate) {
//        DeliveryOrderDestination deliveryOrderDestination = orderCoordinate.getOrder().getDestination();
//        deliveryOrderDestination.setLastLocation(orderCoordinate);
//        deliveryOrderDestinationRepository.save(deliveryOrderDestination);
    }

    @Override
    public String changeDestination(String id, DeliveryOrderDestinationDto destinationDto) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        securityUtil.checkUserAccess(deliveryOrder);
        if (deliveryOrder.getStatus().compareTo(DeliveryOrderStatus.PENDING) == 0) {
            return orderCommand.changeDestination(id, destinationDto);
        }
        throw new DeliveryOrderStatusException("Order status is not Pending");
    }
}
