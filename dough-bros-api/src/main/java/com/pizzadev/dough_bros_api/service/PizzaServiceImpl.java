package com.pizzadev.dough_bros_api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pizzadev.dough_bros_api.model.Pizza;
import com.pizzadev.dough_bros_api.repository.PizzaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService{

    private final PizzaRepository pizzaRepository;


    @Override
    public List<Pizza> readPizzas(List<Long> requestedIds) {
        List<Pizza> pizzas = pizzaRepository.findAllById(requestedIds);
        if (pizzas.size() != requestedIds.stream().distinct().count() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " One or more pizzas don´t exist");
        }
        return pizzas; 
    }

    @Override
    public BigDecimal calculateTotal(List<Long> requestedIds, List<Pizza> pizzasFound) {
        
        Map <Long,Pizza> requestedPizzas = pizzasFound.stream().collect(Collectors.toMap(Pizza::getId, pizza -> pizza));

        BigDecimal total = BigDecimal.ZERO;

        for (Long id : requestedIds) {
            Pizza pizza = requestedPizzas.get(id);
            total = total.add(pizza.getPrice());
        }
        return total;

        
    }
    
}
