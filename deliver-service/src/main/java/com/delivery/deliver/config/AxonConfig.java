package com.delivery.deliver.config;

import com.delivery.deliver.aggregates.OrderAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

//    @Bean
//    EventSourcingRepository<DeliveryOrderAggregate> deliveryOrderAggregateEventSourcingRepository(EventStore eventStore) {
//        EventSourcingRepository<DeliveryOrderAggregate> repository =
//                EventSourcingRepository.builder(DeliveryOrderAggregate.class).eventStore(eventStore).build();
//        return repository;
//    }

    @Bean
    EventSourcingRepository<OrderAggregate> accountAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<OrderAggregate> repository = EventSourcingRepository.builder(OrderAggregate.class).eventStore(eventStore).build();
        return repository;
    }
}
