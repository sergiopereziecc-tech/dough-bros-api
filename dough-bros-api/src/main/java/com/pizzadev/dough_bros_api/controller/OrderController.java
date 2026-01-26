package com.pizzadev.dough_bros_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

@RestController
public class OrderController {
    
    @GetMapping("/api/test-pizza")
    public PizzaOrder getTestPizza(){
        return new PizzaOrder("Sergio", "Carbonara", OrderStatus.RECEIVED, 20.99);
    }

}
