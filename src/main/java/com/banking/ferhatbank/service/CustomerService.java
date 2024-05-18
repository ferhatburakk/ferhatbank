package com.banking.ferhatbank.service;

import com.banking.ferhatbank.entity.Customer;
import com.banking.ferhatbank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Customer Not Found"));
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getByCustomerCode(String customerCode) {
        return customerRepository.findByCustomerCode(customerCode).orElseThrow(() -> new NoSuchElementException("Customer Not Found"));
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }


}
