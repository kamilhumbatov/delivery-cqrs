package com.delivery.deliver.service.handlers;

import com.delivery.CurrentUserService;
import com.delivery.deliver.domain.DeliveryOrder;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.queries.GetAssignerQuery;
import com.delivery.deliver.queries.GetOrderQuery;
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
        GetOrderQuery getOrderQuery = new GetOrderQuery();
        getOrderQuery.setId(id);
        return queryGateway.query(getOrderQuery,
                ResponseTypes.instanceOf(DeliveryOrderDto.class)).join();
    }

    @QueryHandler
    private DeliveryOrderDto getOrderQuery(GetOrderQuery getOrderQuery) {
        DeliveryOrder deliveryOrder = orderService.findById(getOrderQuery.getId());
        return mapper.toDto(deliveryOrder);
    }

    @Override
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        GetOwnerQuery getOwnerQuery = new GetOwnerQuery();
        return queryGateway.query(getOwnerQuery,
                ResponseTypes.multipleInstancesOf(DeliveryOrderDto.class))
                .join();
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
        return queryGateway.query(getAssignerQuery,
                ResponseTypes.multipleInstancesOf(DeliveryOrderDto.class))
                .join();
    }

    @QueryHandler
    private List<DeliveryOrderDto> getAssignerQuery(GetAssignerQuery getAssignerQuery) {
        return orderService.findAllOrdersByAssignee(currentUserService.getCurrentUser())
                .stream()
                .map(DeliveryOrderMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
