package com.banking.ferhatbank.controller;

import com.banking.ferhatbank.entity.Customer;
import com.banking.ferhatbank.service.CustomerService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customer));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
    }

    @GetMapping("getByCustomerCode/{customerCode}")
    public ResponseEntity<Customer> getByCustomerCode(@PathVariable("customerCode") String customerCode) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getByCustomerCode(customerCode));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted succesfully.");
    }
}
