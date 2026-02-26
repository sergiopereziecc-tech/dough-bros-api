package com.pizzadev.dough_bros_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.Customer;
import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.repository.CustomerRepository;
import com.pizzadev.dough_bros_api.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    private static final Map<String, Double> MENU = Map.of(
            "MARGARITA", 12.0,
            "CARBONARA", 15.0,
            "BARBACOA", 18.0);

    // C
    @Override
    public PizzaOrder create(OrderRequest request) {
        // Constructor generates id, set Initial Status and copy data
        PizzaOrder newOrder = new PizzaOrder(request);
        // Calculate price
        newOrder.setPrice(getPriceFromMenu(request.getPizzaType()) * request.getQuantity());
        Customer customerFound = customerService.findById(request.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException(
                        "The customer with Id : " + request.getCustomerId() + "cannot be found in our database"));
        newOrder.setCustomer(customerFound);
        return orderRepository.save(newOrder);
    }

    // R
    @Override
    public List<PizzaOrder> findAll() {
        return orderRepository.findAll();
    }

    // U
    @Override
    public Optional<PizzaOrder> update(Long id, OrderRequest request) {
        return orderRepository.findById(id).map(orderFound -> {
            if (!orderFound.getStatus().equals(OrderStatus.RECEIVED))
                throw new IllegalStateException(
                        "The order is already in the kitchen or sent. You cannot modify it anymore");
            double newPrice = getPriceFromMenu(request.getPizzaType()) * request.getQuantity();
            orderFound.updateFromRequest(request, newPrice);
            return orderRepository.save(orderFound);
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
    public Double getPriceFromMenu(String typePizza) {

        return Optional.ofNullable(MENU.get(typePizza.toUpperCase()))
            .orElseThrow(()-> new IllegalArgumentException(("Sorry, We do not have that pizza " + typePizza)));

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
