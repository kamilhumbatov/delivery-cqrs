package com.delivery.aggregates;

import com.delivery.commands.ChangeStatusDeliveryOrderCommand;
import com.delivery.enums.DeliveryOrderStatus;
import com.delivery.events.DeliverOrderStatusChangeEvent;
import com.delivery.service.DeliveryOrderService;
import com.delivery.user.domain.DeliveryOrder;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class DeliveryOrderAggregate {

    @AggregateIdentifier
    private Long id;

    private DeliveryOrderStatus status;

    @Autowired
    private DeliveryOrderService orderService;

    @CommandHandler
    public DeliveryOrderAggregate(ChangeStatusDeliveryOrderCommand changeStatusDeliveryOrderCommand) {
        DeliveryOrder deliveryOrder = changeStatusDeliveryOrderCommand.getOrder();
        deliveryOrder.setStatus(changeStatusDeliveryOrderCommand.getStatus());
        AggregateLifecycle.apply(new DeliverOrderStatusChangeEvent(deliveryOrder));
    }

    @EventSourcingHandler
    protected void on(DeliverOrderStatusChangeEvent deliverOrderStatusChangeEvent) {
        DeliveryOrder deliveryOrder = deliverOrderStatusChangeEvent.getOrder();
        this.id = deliveryOrder.getId();
        this.status = deliveryOrder.getStatus();
        orderService.save(deliveryOrder);
    }
}
