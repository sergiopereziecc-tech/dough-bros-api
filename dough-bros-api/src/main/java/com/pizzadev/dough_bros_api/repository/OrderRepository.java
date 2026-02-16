package com.pizzadev.dough_bros_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;

@Repository
public interface OrderRepository extends JpaRepository<PizzaOrder,String>{

    
} 

   

