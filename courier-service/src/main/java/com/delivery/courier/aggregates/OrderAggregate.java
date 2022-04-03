package com.delivery.courier.aggregates;

import com.delivery.courier.commands.CreateAccountCommand;
import com.delivery.courier.commands.CreditMoneyCommand;
import com.delivery.courier.commands.DebitMoneyCommand;
import com.delivery.courier.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateAccountCommand createAccountCommand){
        System.out.println("CreateAccountCommand");
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(),
                createAccountCommand.getAccountBalance(), createAccountCommand.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        System.out.println("AccountCreatedEvent");
        this.id = accountCreatedEvent.getId();
        this.accountBalance = accountCreatedEvent.getAccountBalance();
        this.currency = accountCreatedEvent.getCurrency();
        this.status = String.valueOf(Status.CREATED);

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent){
        System.out.println("AccountActivatedEvent");
        this.status = String.valueOf(accountActivatedEvent.getStatus());
    }

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        System.out.println("CreditMoneyCommand");
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.getId(), creditMoneyCommand.getCreditAmount(),
                creditMoneyCommand.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent){
        System.out.println("MoneyCreditedEvent");
        if (this.accountBalance < 0 & (this.accountBalance + moneyCreditedEvent.getCreditAmount()) >= 0){
            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
        }

        this.accountBalance += moneyCreditedEvent.getCreditAmount();
    }

    @CommandHandler
    protected void on(DebitMoneyCommand debitMoneyCommand){
        System.out.println("DebitMoneyCommand");
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.getId(), debitMoneyCommand.getDebitAmount(), debitMoneyCommand.getCurrency()));
    }

    @EventSourcingHandler
    protected void on(MoneyDebitedEvent moneyDebitedEvent){
        System.out.println("MoneyDebitedEvent");
        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.getDebitAmount()) < 0){
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }

        this.accountBalance -= moneyDebitedEvent.getDebitAmount();

    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent accountHeldEvent){
        System.out.println("AccountHeldEvent");
        this.status = String.valueOf(accountHeldEvent.getStatus());
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
