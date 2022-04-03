package com.delivery.deliver.service.commands;

import com.delivery.deliver.commands.CreateAccountCommand;
import com.delivery.deliver.commands.CreditMoneyCommand;
import com.delivery.deliver.commands.DebitMoneyCommand;
import com.delivery.deliver.dto.commands.AccountCreateDTO;
import com.delivery.deliver.dto.commands.MoneyCreditDTO;
import com.delivery.deliver.dto.commands.MoneyDebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(CreateAccountCommand.builder()
                .id(UUID.randomUUID().toString())
                .accountBalance(accountCreateDTO.getStartingBalance())
                .currency(accountCreateDTO.getCurrency())
                .build());
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(CreditMoneyCommand.builder()
                .id(accountNumber)
                .creditAmount(moneyCreditDTO.getCreditAmount())
                .currency(moneyCreditDTO.getCurrency())
                .build());
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(DebitMoneyCommand.builder()
                .id(accountNumber)
                .currency(moneyDebitDTO.getCurrency())
                .debitAmount(moneyDebitDTO.getDebitAmount())
                .build());
    }
}
