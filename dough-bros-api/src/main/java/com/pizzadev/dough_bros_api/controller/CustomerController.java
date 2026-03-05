package com.pizzadev.dough_bros_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzadev.dough_bros_api.model.Customer;
import com.pizzadev.dough_bros_api.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "List all the customer")
    @ApiResponse(responseCode = "200",description = "Successful operation")
    @GetMapping()
    public ResponseEntity<List<Customer>> getAll () {
        return ResponseEntity.ok(customerService.findAll());
    }
    @Operation(summary = "List customer by id")
    @ApiResponse(responseCode = "200",description = "Successful operation")
    @ApiResponse(responseCode = "404",description = "Not found operation")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return customerService.findById(id)
            .map(customer-> ResponseEntity.ok(customer))
            .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Updates a customer information")
    @ApiResponse(responseCode = "200",description = "Successful operation")
    @ApiResponse(responseCode = "404",description = "Not found operation")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
       return customerService.update(id, customer)
            .map(client -> ResponseEntity.ok(client))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Register a new customer")
    @ApiResponse(responseCode = "201",description = "Successful operation")
    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customer));
        
        
    }
    @Operation(summary = "Delete customer by id")
    @ApiResponse(responseCode = "204",description = "Successful operation") 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
    

    
}
