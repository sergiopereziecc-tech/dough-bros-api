package com.pizzadev.dough_bros_api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.model.Customer;
import com.pizzadev.dough_bros_api.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
        
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("The customer with id: " + id + " it is not in our database");
        }

    }
    @Override
    public Optional<Customer> update(Long id, Customer customer) {
        return customerRepository.findById(id).map(customerFound -> {
            customerFound.setName(customer.getName());
            customerFound.setEmail(customer.getEmail());
            System.out.println("ID guardado: " + customerFound.getId());
            return customerRepository.save(customerFound);
        });
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
            
    }


    
}
