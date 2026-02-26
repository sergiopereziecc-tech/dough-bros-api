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
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/api/orders")
    public List<PizzaOrder> listAll() {
        return orderService.findAll();
    }

    @GetMapping("/api/orders/{id}")
    public PizzaOrder findById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping("/api/orders")
    public PizzaOrder submitOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.create(request);

    }

    // {id} dinamic parameter
    // @PathVariable the id comes from the url
    @DeleteMapping("/api/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }

    @PutMapping("/api/orders/{id}")
    public PizzaOrder updateOrder(@PathVariable Long id,@Valid @RequestBody OrderRequest request) {
        return orderService.update(id, request);

        
    }

    @PatchMapping("/api/orders/{id}/next")
    public PizzaOrder advanceOrder(@PathVariable Long id) {
        return orderService.statusProgress(id);

    }

}
