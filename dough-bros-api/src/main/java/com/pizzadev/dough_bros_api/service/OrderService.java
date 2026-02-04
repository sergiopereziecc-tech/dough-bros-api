package com.pizzadev.dough_bros_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private static final Map<String, Double> MENU = Map.of(
            "Margarita", 12.0,
            "Carbonara", 15.0,
            "Barbacoa", 18.0);

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<PizzaOrder> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public void createOrder(PizzaOrder order) {
        
        Double pricePizza = getPriceFromMenu(order.getPizzaType());
        order.setPrice(pricePizza);
        order.setStatus(OrderStatus.RECEIVED);
        orderRepository.createOrder(order);
    }
    public Double getPriceFromMenu(String typePizza){
        Double pricePizza = MENU.get(typePizza);
        if (pricePizza == null) {
            throw new IllegalArgumentException("Sorry, We do not have that pizza " + typePizza);
        }else{
            return pricePizza;
        }
        
    }
    public void deleteOrder(String id) {
        orderRepository.deleteOrder(id);
    }

    public void updateOrder(String id, PizzaOrder newOrder) {
        PizzaOrder orderFound = findById(id);
        if (orderFound != null) {
            orderFound.setCustomerName(newOrder.getCustomerName());
            orderFound.setPizzaType(newOrder.getPizzaType());
            orderFound.setPrice(newOrder.getPrice());
            orderFound.setStatus(newOrder.getStatus());
        }

    }

    public PizzaOrder findById(String id) {
        PizzaOrder order = orderRepository.findById(id);
        if(order == null){
            throw new NoSuchElementException("We couldnt find the order with ID : " + order.getId());

        }
        return order;
    }
}
