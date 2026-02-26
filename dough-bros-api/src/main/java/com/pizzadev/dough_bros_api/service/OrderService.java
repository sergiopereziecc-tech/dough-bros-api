package com.pizzadev.dough_bros_api.service;

import java.util.List;
import java.util.Optional;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

public interface OrderService {
    
    PizzaOrder create(OrderRequest request);
    List<PizzaOrder> findAll();
    Optional<PizzaOrder> update(Long id, OrderRequest request);
    Optional<PizzaOrder> delete(Long id);
    Optional<PizzaOrder> findById(Long id);
    Double getPriceFromMenu(String typePizza);
    Optional<PizzaOrder> statusProgress(Long id);


}
