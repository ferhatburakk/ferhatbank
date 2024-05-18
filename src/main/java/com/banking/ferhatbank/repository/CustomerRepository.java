package com.banking.ferhatbank.repository;

import com.banking.ferhatbank.entity.Customer;
import com.banking.ferhatbank.service.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerCode(String customerCode);
}
