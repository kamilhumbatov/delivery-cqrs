package com.delivery.deliver.controller;

import com.delivery.deliver.aggregates.OrderAggregate;
import com.delivery.deliver.dto.DeliveryOrderDto;
import com.delivery.deliver.queries.GetDeliveryOrderQuery;
import com.delivery.deliver.service.DeliveryOrderCommandService;
import com.delivery.util.RoleName;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("delivery-orders")
@RequiredArgsConstructor
public class DeliveryOrderController {

    private final DeliveryOrderCommandService service;
    private final QueryGateway queryGateway;

//    @GetMapping("/{id}")
//    public DeliveryOrderDto getOrder(@PathVariable String id) {
//        return service.getOrder(id);
//    }

    @Secured(RoleName.ROLE_CUSTOMER)
    @GetMapping("/owner")
    public List<DeliveryOrderDto> findAllOrdersByOwner() {
        return service.findAllOrdersByOwner();
    }

    @Secured(RoleName.ROLE_COURIER)
    @GetMapping("/assignee")
    public List<DeliveryOrderDto> findAllOrdersByAssignee() {
        return service.findAllOrdersByAssignee();
    }

    @GetMapping("/{accountNumber}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") String accountNumber) {
        return service.listEventsForAccount(accountNumber);
    }

    @GetMapping("/{id}")
    public OrderAggregate getDeliveryOrder(@PathVariable String id) throws InterruptedException, ExecutionException {
        CompletableFuture<OrderAggregate> future = queryGateway.query(new GetDeliveryOrderQuery(id), OrderAggregate.class);
        return future.get();
    }
}
