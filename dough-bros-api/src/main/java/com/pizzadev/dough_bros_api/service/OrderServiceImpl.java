package com.pizzadev.dough_bros_api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.Customer;
import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.Pizza;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.repository.CustomerRepository;
import com.pizzadev.dough_bros_api.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final PizzaService pizzaService;

    

    // C
    @Override
    public PizzaOrder create(OrderRequest request) {
        List<Pizza> pizzas = pizzaService.readPizzas(request.getPizzaIds());
        BigDecimal total = pizzaService.calculateTotal(request.getPizzaIds(),pizzas);

        return customerService.findById(request.getCustomerId()).map(customerFound ->{
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setCustomer(customerFound);
            pizzaOrder.setPizzas(pizzas);
            pizzaOrder.setTotalPrice(total);
            return orderRepository.save(pizzaOrder);
        }).orElseThrow(()-> new RuntimeException("Customer Not Found"));

        
        
    }

    // R
    @Override
    public List<PizzaOrder> findAll() {
        return orderRepository.findAll();
    }

    // U
    @Override
    public Optional<PizzaOrder> update(Long id, OrderRequest request) {
        return orderRepository.findById(id).map(order -> {
            List<Pizza> pizzas = pizzaService.readPizzas(request.getPizzaIds());
            BigDecimal total = pizzaService.calculateTotal(request.getPizzaIds(), pizzas);
            order.setPizzas(pizzas);
            order.setTotalPrice(total);
            return orderRepository.save(order);
        });
        
        

    }

    // D
    @Override
    public Optional<PizzaOrder> delete(Long id) {
        return orderRepository.findById(id).map(orderFound -> {
            if (!orderFound.getStatus().equals(OrderStatus.RECEIVED))
                throw new IllegalStateException("Order is already in the kitchen or sent out. You cannot cancel it");
            orderRepository.deleteById(id);
            return orderFound;
        });

    }

    @Override
    public Optional<PizzaOrder> findById(Long id) {
        return orderRepository.findById(id);
    }


    @Override
    public Optional<PizzaOrder> statusProgress(Long id) {
        return orderRepository.findById(id).map(orderFound -> {
            OrderStatus currentStatus = orderFound.getStatus();
            switch (currentStatus) {
                case RECEIVED -> orderFound.setStatus(OrderStatus.IN_PROGRESS);
                case IN_PROGRESS -> orderFound.setStatus(OrderStatus.READY);
                case READY -> orderFound.setStatus(OrderStatus.DELIVERED);
                case DELIVERED -> throw new IllegalStateException("Pizza delivered, cannot advanced any more");
                default -> throw new IllegalStateException("Status not recognized");
            }
            return orderRepository.save(orderFound);
        });

    }

}
