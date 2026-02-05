package com.pizzadev.dough_bros_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.service.OrderService;
import com.pizzadev.dough_bros_api.service.OrderServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class OrderController {
    
    private final OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }

    @GetMapping("/api/orders")
    public List<PizzaOrder> listAll() {
        return service.findAll();
    }

    @GetMapping("/api/orders/{id}")
    public PizzaOrder findById(@PathVariable String id) {
        return service.findById(id);
    }
    

    @PostMapping("/api/orders")
    public PizzaOrder submitOrder(@Valid @RequestBody OrderRequest request) {
        return service.create(request);
        
    }
    
    
    //{id} dinamic parameter
    //@PathVariable the id comes from the url
    @DeleteMapping("/api/orders/{id}")
    public void deleteOrder(@PathVariable String id){
        service.delete(id);
    }

    @PutMapping("/api/orders/{id}")
    public PizzaOrder updateOrder(@PathVariable String id, @RequestBody OrderRequest request) {
        service.update(id, request);
        
        return service.findById(id);
    }

    @PatchMapping("/api/orders/{id}/status")
    public PizzaOrder advanceOrder(@PathVariable String id){
        

    }
    

}
