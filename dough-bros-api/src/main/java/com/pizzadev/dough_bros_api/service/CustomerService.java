package com.pizzadev.dough_bros_api.service;

import java.util.List;
import java.util.Optional;

import com.pizzadev.dough_bros_api.model.Customer;

public interface CustomerService {

    Customer create(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    void delete (Long id);

    Optional<Customer> update(Long id, Customer customer);

    
    
}
