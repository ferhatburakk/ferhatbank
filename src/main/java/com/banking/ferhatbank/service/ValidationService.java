package com.banking.ferhatbank.service;

import com.banking.ferhatbank.entity.Account;
import com.banking.ferhatbank.request.MoneyTransferMessageRequest;
import com.banking.ferhatbank.service.rabbitmq.MessageConsumer;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final CustomerService customerService;
    private final AccountService accountService;

    public ValidationService(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    public boolean validateMoneyTransferMessage(MoneyTransferMessageRequest moneyTransferMessageRequest) {

        Account sourceAccount = accountService.getByAccountNumber(moneyTransferMessageRequest.getSourceAccNo());
        Account destAccount = accountService.getByAccountNumber(moneyTransferMessageRequest.getDestAccNo());
        Long amount = moneyTransferMessageRequest.getAmount();
        if (sourceAccount.getBalance() > moneyTransferMessageRequest.getAmount()) {
            accountService.whitdrawMoneyByAccountNumber(sourceAccount.getAccountNumber(), amount);
            accountService.depositMoneyByAccountNumber(destAccount.getAccountNumber(), amount);
            return true;
        } else {
            return false;
        }

    }
}
