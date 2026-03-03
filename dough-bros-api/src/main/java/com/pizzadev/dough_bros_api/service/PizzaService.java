package com.pizzadev.dough_bros_api.service;

import java.math.BigDecimal;
import java.util.List;

import com.pizzadev.dough_bros_api.model.Pizza;

public interface PizzaService {
    public List<Pizza> readPizzas(List<Long> pizzaIds);

    public BigDecimal calculateTotal(List<Long> pizzaIds, List<Pizza> pizzasFound);

}
