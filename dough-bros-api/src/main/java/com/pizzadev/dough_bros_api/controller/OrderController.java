package com.pizzadev.dough_bros_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.service.OrderServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class OrderController {
    
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @GetMapping("/api/orders")
    public List<PizzaOrder> listAll() {
        return orderService.findAll();
    }

    @GetMapping("/api/orders/{id}")
    public PizzaOrder findById(@PathVariable String id) {
        return orderService.findById(id);
    }
    

    @PostMapping("/api/orders")
    public PizzaOrder submitOrder(@Valid @RequestBody PizzaOrder order) {
        orderService.create(order);
        return order;
    }
    
    
    //{id} dinamic parameter
    //@PathVariable the id comes from the url
    @DeleteMapping("/api/orders/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.delete(id);
    }

    @PutMapping("/api/orders/{id}")
    public PizzaOrder updateOrder(@PathVariable String id, @RequestBody PizzaOrder newOrder) {
        orderService.update(id, newOrder);
        
        return orderService.findById(id);
    }
    

}
