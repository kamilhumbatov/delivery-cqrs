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


}
