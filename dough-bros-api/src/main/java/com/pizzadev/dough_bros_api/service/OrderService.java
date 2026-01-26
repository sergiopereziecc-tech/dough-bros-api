package com.pizzadev.dough_bros_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

@Service
public class OrderService {
    
    private final List<PizzaOrder> orders = new ArrayList<>();

    public OrderService(){
        createOrder(new PizzaOrder("Perez", "Barbacoa", OrderStatus.RECEIVED, 20.99));
        createOrder(new PizzaOrder("Antonien", "Chorizo", OrderStatus.RECEIVED, 20.99));
    }


    public List<PizzaOrder> getAllOrders(){
        return this.orders;
    }

    public void createOrder(PizzaOrder order){
        this.orders.add(order);

    }
}
