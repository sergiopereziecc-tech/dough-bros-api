package com.pizzadev.dough_bros_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PizzaOrder>> listAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<PizzaOrder> findById(@PathVariable Long id) {
        return orderService.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/api/orders")
    public ResponseEntity<PizzaOrder> submitOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));

    }

    // {id} dinamic parameter
    // @PathVariable the id comes from the url
    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return orderService.delete(id).map(order->ResponseEntity.noContent().<Void>build())
            .orElseGet(()-> ResponseEntity.notFound().build());

    }

    @PutMapping("/api/orders/{id}")
    public ResponseEntity<PizzaOrder> updateOrder(@PathVariable Long id,@Valid @RequestBody OrderRequest request) {

        return orderService.update(id, request).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());

        
    }

    @PatchMapping("/api/orders/{id}/next")
    public ResponseEntity<PizzaOrder> advanceOrder(@PathVariable Long id) {
        return orderService.statusProgress(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }

}
