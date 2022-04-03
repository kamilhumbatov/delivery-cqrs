package com.delivery.deliver.queries;

import com.delivery.deliver.aggregates.OrderAggregate;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OrderAggregateProjector {

    private final Repository<OrderAggregate> libraryRepository;

    @QueryHandler
    public OrderAggregate getOrderAggregate(GetDeliveryOrderQuery query) throws InterruptedException, ExecutionException {
        CompletableFuture<OrderAggregate> future = new CompletableFuture<OrderAggregate>();
        libraryRepository.load(query.getId()).execute(future::complete);
        return future.get();
    }

}