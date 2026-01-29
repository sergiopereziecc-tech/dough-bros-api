package com.pizzadev.dough_bros_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.repository.OrderRepository;

@Service
public class OrderService {

    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<PizzaOrder> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void createOrder(PizzaOrder order) {
        orderRepository.createOrder(order);

    }

    public void deleteOrder(String id) {
        orderRepository.deleteOrder(id);
    }

    public void updateOrder(String id, PizzaOrder newOrder){
        orderRepository.updateOrder(id, newOrder);

    }

    public PizzaOrder findById(String id) {
        return orderRepository.findById(id);
    }
}
