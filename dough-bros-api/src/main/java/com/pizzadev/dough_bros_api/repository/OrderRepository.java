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
        createOrder(new PizzaOrder("Perez", "Barbacoa", OrderStatus.RECEIVED, 20.99));
        createOrder(new PizzaOrder("Antonien", "Chorizo", OrderStatus.RECEIVED, 20.99));
    }

    public List<PizzaOrder> getAllOrders() {
        return this.orders;
    }

    public void createOrder(PizzaOrder order) {
        this.orders.add(order);

    }

    public void deleteOrder(String id) {
        this.orders.removeIf(order -> order.getId().equals(id));
    }

    
    public PizzaOrder findById(String id) {
        for (int i = 0; i < this.orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                return orders.get(i);
            }
        }
        return null;
    }
}
