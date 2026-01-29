package com.pizzadev.dough_bros_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

@Service
public class OrderService {

    private final List<PizzaOrder> orders = new ArrayList<>();

    public OrderService() {
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

    public void updateOrder(String id, PizzaOrder newOrder){
        PizzaOrder orderFound = findById(id);
        if (orderFound != null) {
            orderFound.setCustomerName(newOrder.getCustomerName());
            orderFound.setPizzaType(newOrder.getPizzaType());
            orderFound.setPrice(newOrder.getPrice());
            orderFound.setStatus(newOrder.getStatus());
        }

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
