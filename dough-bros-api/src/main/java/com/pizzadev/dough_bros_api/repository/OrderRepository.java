package com.pizzadev.dough_bros_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

@Repository
public class OrderRepository {
    private final List<PizzaOrder> orders = new ArrayList<>();

    public OrderRepository() {
        createOrder(new PizzaOrder("Perez", "Barbacoa", OrderStatus.RECEIVED, 20.99,1));
        createOrder(new PizzaOrder("Antonien", "Chorizo", OrderStatus.RECEIVED, 20.99,1));
    }

    public List<PizzaOrder> getAllOrders() {
        return this.orders;
    }

    public PizzaOrder createOrder(PizzaOrder order) {
         this.orders.add(order);
         return order;

    }

    public void deleteOrder(String id) {
        this.orders.removeIf(order -> order.getId().equals(id));
    }

    public PizzaOrder findById(String id) {
        return this.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
