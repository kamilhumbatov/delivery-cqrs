package com.delivery.deliver.aggregates;

import com.delivery.deliver.commands.ChangeCoordinateCommand;
import com.delivery.deliver.commands.CreateAccountCommand;
import com.delivery.deliver.commands.CreateOrderCommand;
import com.delivery.deliver.commands.CreditMoneyCommand;
import com.delivery.deliver.enums.DeliveryOrderStatus;
import com.delivery.deliver.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    private String owner;

    private String latitude;

    private String longitude;

    private String currentLatitude;

    private String currentLongitude;

    private DeliveryOrderStatus statusDelivery;

    private List<String> coordinates;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        System.out.println("CreateOrderCommand");
        var event = DeliverOrderCreatedEvent.builder()
                .orderId(command.getOrderId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .owner(command.getOwner())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeliverOrderCreatedEvent event) {
        System.out.println("DeliverOrderCreatedEvent");
        this.id = event.getOrderId();
        this.owner = event.getOwner();
        this.latitude = event.getLatitude();
        this.longitude = event.getLongitude();
        this.statusDelivery = DeliveryOrderStatus.CREATED;
        this.coordinates = new ArrayList<>();
        AggregateLifecycle.apply(new DeliverOrderActivatedEvent(this.id, DeliveryOrderStatus.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(DeliverOrderActivatedEvent accountActivatedEvent) {
        System.out.println("AccountActivatedEvent");
        this.statusDelivery = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public OrderAggregate(ChangeCoordinateCommand command) {
        System.out.println("ChangeCoordinateCommand");
        var event = DeliverOrderCoordinateChangedEvent.builder()
                .orderId(command.getOrderId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(DeliverOrderCoordinateChangedEvent event) {
        System.out.println("DeliverOrderCoordinateChangedEvent");
        this.currentLatitude = event.getLatitude();
        this.currentLongitude = event.getLongitude();
        //this.coordinates.add(event.getLatitude());
    }

    @CommandHandler
    public OrderAggregate(CreateAccountCommand createAccountCommand) {
        System.out.println("CreateAccountCommand");
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(),
                createAccountCommand.getAccountBalance(), createAccountCommand.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent) {
        System.out.println("AccountCreatedEvent");
        this.id = accountCreatedEvent.getId();
        this.accountBalance = accountCreatedEvent.getAccountBalance();
        this.currency = accountCreatedEvent.getCurrency();
        this.status = String.valueOf(Status.CREATED);

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent) {
        System.out.println("AccountActivatedEvent");
        this.status = String.valueOf(accountActivatedEvent.getStatus());
    }

    @CommandHandler
    protected void on(CreditMoneyCommand command) {
        System.out.println("CreditMoneyCommand");
//        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.getId(), creditMoneyCommand.getCreditAmount(),
//                creditMoneyCommand.getCurrency()));
        System.out.println("ChangeCoordinateCommand");
        var event = DeliverOrderCoordinateChangedEvent.builder()
                .orderId(command.getId())
                .latitude(command.getLatitude())
                .longitude(command.getLongitude())
                .build();
        AggregateLifecycle.apply(event);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
