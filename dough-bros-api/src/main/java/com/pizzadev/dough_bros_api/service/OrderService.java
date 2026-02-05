package com.pizzadev.dough_bros_api.service;

import java.util.List;

import com.pizzadev.dough_bros_api.model.PizzaOrder;

public interface OrderService {
    
    PizzaOrder create(PizzaOrder order);
    List<PizzaOrder> findAll();
    PizzaOrder update(String id, PizzaOrder newOrder);
    void delete(String id);
    PizzaOrder findById(String id);
    Double getPriceFromMenu(String typePizza);


}
