package com.delivery.deliver.service.handlers;

import com.delivery.CurrentUserService;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDestinationDto;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.queries.GetAssignerQuery;
import com.delivery.deliver.queries.GetOwnerQuery;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.mapper.DeliveryOrderMapper;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryOrderQueryHandlerImpl implements DeliveryOrderQueryHandlerService {

    private final DeliveryOrderMapper mapper;
    private final DeliveryOrderService orderService;
    private final CurrentUserService currentUserService;
    private final QueryGateway queryGateway;

    @Override
    public DeliveryOrderDto getOrder(String id) {
        DeliveryOrder deliveryOrder = orderService.findById(id);
        return mapper.toDto(deliveryOrder);
    }

    @Override
    public DeliveryOrderDto changeDestination(String id, DeliveryOrderDestinationDto destinationDto) {
        return null;
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        GetOwnerQuery getOwnerQuery = new GetOwnerQuery();
        List<DeliveryOrderDto> deliveryOrderDtoList =
                queryGateway.query(getOwnerQuery,
                        ResponseTypes.multipleInstancesOf(DeliveryOrderDto.class))
                        .join();
        return deliveryOrderDtoList;
    }

    @QueryHandler
    private List<DeliveryOrderDto> getOwnerQuery(GetOwnerQuery getOwnerQuery) {
        return orderService.findAllOrdersByOwner(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByAssignee() {
        GetAssignerQuery getAssignerQuery = new GetAssignerQuery();
        List<DeliveryOrderDto> deliveryOrderDtoList =
                queryGateway.query(getAssignerQuery,
                        ResponseTypes.multipleInstancesOf(DeliveryOrderDto.class))
                        .join();
        return deliveryOrderDtoList;
    }

    @QueryHandler
    private List<DeliveryOrderDto> getAssignerQuery(GetAssignerQuery getAssignerQuery) {
        return orderService.findAllOrdersByAssignee(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
