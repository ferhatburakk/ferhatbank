package com.banking.ferhatbank;

import com.banking.ferhatbank.entity.Account;
import com.banking.ferhatbank.entity.Customer;
import com.banking.ferhatbank.service.AccountService;
import com.banking.ferhatbank.service.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InitDB {
    private final CustomerService customerService;

    private final AccountService accountService;
    public InitDB(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @PostConstruct
    public void initDB(){
        Customer customer1 = new Customer(1L, "Ferhat", "321493", null);
        Customer customer2 = new Customer(2L, "Ahmet", "312452", null);

        customerService.save(customer1);
        customerService.save(customer2);

        Account account1 = new Account(1L, "123421", "TR43243223892234324455", 150l, customer1);
        Account account2 = new Account(2L, "232452", "TR4343345421343232", 5000l, customer2);

        accountService.save(account1);
        accountService.save(account2);


    }
}
