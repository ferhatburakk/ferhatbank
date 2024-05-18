package com.banking.ferhatbank.controller;

import com.banking.ferhatbank.entity.Account;
import com.banking.ferhatbank.request.MoneyTransferMessageRequest;
import com.banking.ferhatbank.service.AccountService;
import com.banking.ferhatbank.service.ValidationService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final ValidationService validationService;

    public AccountController(AccountService accountService, ValidationService validationService) {
        this.accountService = accountService;
        this.validationService = validationService;
    }

    @PostMapping("/save")
    public ResponseEntity<Account> save(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.save(account));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Account> getById(@PathParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAll());
    }

    @GetMapping("/getByAccountNumber/{accountNumber}")
    public ResponseEntity<Account> getByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getByAccountNumber(accountNumber));
    }

    @GetMapping("/getAllByCustomerCode/{customerCode}")
    public ResponseEntity<List<Account>> getAllByCustomerCode(@PathVariable("customerCode") String customerCode) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllByCustomerCode(customerCode));
    }

    @PutMapping("/depositByAccountNumber/{accountNumber}/{amount}")
    public ResponseEntity<Account> depositByAccountNumber(@PathVariable("accountNumber") String accountNumber, @PathVariable("amount") Long amount) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.depositMoneyByAccountNumber(accountNumber, amount));
    }

    @PutMapping("/whitdrawByAccountNumber/{accountNumber}/{amount}")
    public ResponseEntity<Account> whitdrawByAccountNumber(@PathVariable("accountNumber") String accountNumber, @PathVariable("amount") Long amount) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.whitdrawMoneyByAccountNumber(accountNumber, amount));
    }

    @PostMapping("/sendMoney")
    public ResponseEntity<?> sendMoney(@RequestBody MoneyTransferMessageRequest moneyTransferMessageRequest){
        accountService.sendMoney(moneyTransferMessageRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Money transfer!" );

    }
    @PostMapping("/sendMoney2")
    public ResponseEntity<?> sendMoney2(@RequestBody MoneyTransferMessageRequest moneyTransferMessageRequest){
        boolean response = validationService.validateMoneyTransferMessage(moneyTransferMessageRequest);
        String message = "";
        if (response) {
            message = "success.";
        } else {
            message = "fail.";
        }
        System.err.println(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);

    }
}
