package com.pizzadev.dough_bros_api.service;

import java.util.List;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

public interface OrderService {
    
    PizzaOrder create(OrderRequest request);
    List<PizzaOrder> findAll();
    PizzaOrder update(String id, OrderRequest request);
    void delete(String id);
    PizzaOrder findById(String id);
    Double getPriceFromMenu(String typePizza);


}
