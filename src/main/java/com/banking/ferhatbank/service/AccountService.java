package com.banking.ferhatbank.service;

import com.banking.ferhatbank.entity.Account;
import com.banking.ferhatbank.repository.AccountRepository;
import com.banking.ferhatbank.request.MoneyTransferMessageRequest;
import com.banking.ferhatbank.service.rabbitmq.MessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final MessageProducer messageProducer;

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepository, CustomerService customerService, MessageProducer messageProducer) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.messageProducer = messageProducer;
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Account Not Found"));
    }

    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Account Not Found"));
    }

    public List<Account> getAllByCustomerCode(String customerCode) {
        return customerService.getByCustomerCode(customerCode).getAccountList();
    }

    public Account depositMoneyByAccountNumber(String accountNumber, Long amount) {
        Account account = null;
        if (amount > 0L) {
            account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new NoSuchElementException("Account Not Found"));
            Long currentBalance = account.getBalance();
            Long newBalance = currentBalance + amount;
            account.setBalance(newBalance);
            accountRepository.save(account);
            logger.info("Successfully deposited money.");
        } else {
            throw new IllegalArgumentException("Amount cannot be less than zero!");
        }
        return account;
    }

    public Account whitdrawMoneyByAccountNumber(String accountNumber, Long amount) {
        Account account = getByAccountNumber(accountNumber);
        if (account.getBalance() > amount) {
            Long currentBalance = account.getBalance();
            Long newBalance = currentBalance - amount;
            account.setBalance(newBalance);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Insufficient funds!");
        }
        return account;
    }

    public void sendMoney(MoneyTransferMessageRequest moneyTransferMessageRequest) {
        messageProducer.sendMoneyTransferMessage(moneyTransferMessageRequest);
    }

}
