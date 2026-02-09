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

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private static final Map<String, Double> MENU = Map.of(
            "MARGARITA", 12.0,
            "CARBONARA", 15.0,
            "BARBACOA", 18.0);

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // C
    @Override
    public PizzaOrder create(OrderRequest request) {
        // Constructor generates id, set Initial Status and copy data
        PizzaOrder newOrder = new PizzaOrder(request);
        // Calculate price
        newOrder.setPrice(getPriceFromMenu(request.getPizzaType()) * request.getQuantity());
        return orderRepository.createOrder(newOrder);
    }

    // R
    @Override
    public List<PizzaOrder> findAll() {
        return orderRepository.getAllOrders();
    }

    // U
    @Override
    public PizzaOrder update(String id, OrderRequest request) {
        // If it doenst exist, findById, throws exception
        PizzaOrder orderFound = findById(id);
        if (!orderFound.getStatus().equals(OrderStatus.RECEIVED))
            throw new IllegalStateException("The order is already in the kitchen or sent. You cannot modify it anymore");

        orderFound.setCustomerName(request.getCustomerName());
        orderFound.setPizzaType(request.getPizzaType());
        orderFound.setQuantity(request.getQuantity());

        orderFound.setPrice(getPriceFromMenu(request.getPizzaType()) * request.getQuantity());

        return orderFound;
    }

    // D
    @Override
    public void delete(String id) {
        PizzaOrder order = findById(id);
        if (!order.getStatus().equals(OrderStatus.RECEIVED))
            throw new IllegalStateException("Order is already in the kitchen or sent out. You cannot cancel it");

        orderRepository.deleteOrder(order.getId());
    }

    @Override
    public PizzaOrder findById(String id) {
        PizzaOrder order = orderRepository.findById(id);
        if (order == null) {
            throw new NoSuchElementException("We couldnt find the order with ID : " + id);

        }
        return order;
    }

    @Override
    public Double getPriceFromMenu(String typePizza) {
        Double pricePizza = MENU.get(typePizza);
        if (pricePizza == null) {
            throw new IllegalArgumentException("Sorry, We do not have that pizza " + typePizza);
        } else {
            return pricePizza;
        }

    }

    @Override
    public PizzaOrder statusProgress(String id) {
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
