package com.delivery.deliver.service.handlers;

import com.delivery.security.CurrentUserService;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.exception.DeliveryOrderNotFoundException;
import com.delivery.deliver.queries.GetAssignerQuery;
import com.delivery.deliver.queries.GetOrderQuery;
import com.delivery.deliver.queries.GetOwnerQuery;
import com.delivery.deliver.service.DeliveryOrderService;
import com.delivery.deliver.service.mapper.DeliveryOrderMapper;
import com.delivery.security.util.RoleName;
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
    public DeliveryOrderDto getOrder(String id, String role) {
        GetOrderQuery orderQuery = new GetOrderQuery();
        orderQuery.setId(id);
        orderQuery.setRole(role);
        return queryGateway.query(orderQuery,
                ResponseTypes.instanceOf(DeliveryOrderDto.class)).join();
    }

    @QueryHandler
    private DeliveryOrderDto getOrderQuery(GetOrderQuery orderQuery) {
        switch (orderQuery.getRole()) {
            case RoleName.ROLE_ADMIN:
                return mapper.toDto(orderService.findById(orderQuery.getId()));
            case RoleName.ROLE_COURIER:
                return mapper.toDto(orderService.findByIdAndAssignee(orderQuery.getId()
                        , currentUserService.getCurrentUser()));
            case RoleName.ROLE_CUSTOMER:
                return mapper.toDto(orderService.findByIdAndOwner(orderQuery.getId()
                        , currentUserService.getCurrentUser()));
            default:
                throw new DeliveryOrderNotFoundException(orderQuery.getId());
        }
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
