package com.pizzadev.dough_bros_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.pizzadev.dough_bros_api.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{
    
}
