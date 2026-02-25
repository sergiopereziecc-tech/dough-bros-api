package com.pizzadev.dough_bros_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

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
        return orderRepository.save(newOrder);
    }

    // R
    @Override
    public List<PizzaOrder> findAll() {
        return orderRepository.findAll();
    }

    // U
    @Override
    public PizzaOrder update(Long id, OrderRequest request) {
        
        PizzaOrder orderFound = findById(id);
        if (!orderFound.getStatus().equals(OrderStatus.RECEIVED))
            throw new IllegalStateException("The order is already in the kitchen or sent. You cannot modify it anymore");

        double newPrice = getPriceFromMenu(request.getPizzaType()) * request.getQuantity();
        orderFound.updateFromRequest(request, newPrice);

        return orderRepository.save(orderFound);
    }

    // D
    @Override
    public void delete(Long id) {
        PizzaOrder order = findById(id);
        if (!order.getStatus().equals(OrderStatus.RECEIVED))
            throw new IllegalStateException("Order is already in the kitchen or sent out. You cannot cancel it");

        orderRepository.deleteById(id);
    }

    @Override
    public PizzaOrder findById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(()-> new NoSuchElementException("We couldnt find the order with ID : " + id));
    }

    @Override
    public Double getPriceFromMenu(String typePizza) {
        Double pricePizza = MENU.get(typePizza.toUpperCase());
        if (pricePizza == null) {
            throw new IllegalArgumentException("Sorry, We do not have that pizza " + typePizza);
        } else {
            return pricePizza;
        }

    }

    @Override
    public PizzaOrder statusProgress(Long id) {
        PizzaOrder orderFound = findById(id);
        OrderStatus currentStatus = orderFound.getStatus();

        switch (currentStatus) {
            case RECEIVED -> orderFound.setStatus(OrderStatus.IN_PROGRESS);
            case IN_PROGRESS -> orderFound.setStatus(OrderStatus.READY);
            case READY -> orderFound.setStatus(OrderStatus.DELIVERED);
            case DELIVERED -> throw new IllegalStateException("Pizza delivered, cannot advanced any more");
            default -> throw new IllegalStateException("Status not recognized");
        }

        return orderFound;

    }

}
