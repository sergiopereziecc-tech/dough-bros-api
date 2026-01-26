package com.pizzadev.dough_bros_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/api/orders")
    public List<PizzaOrder> listAll() {
        return orderService.getAllOrders();
    }
    
    

}
