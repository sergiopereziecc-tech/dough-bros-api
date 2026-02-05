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
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    private static final Map<String, Double> MENU = Map.of(
            "Margarita", 12.0,
            "Carbonara", 15.0,
            "Barbacoa", 18.0);

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // C
    public void createOrder(PizzaOrder order) {

        Double pricePizza = getPriceFromMenu(order.getPizzaType());
        order.setPrice(pricePizza);
        order.setStatus(OrderStatus.RECEIVED);
        orderRepository.createOrder(order);
    }

    // R
    public List<PizzaOrder> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    // U
    public PizzaOrder updateOrder(String id, PizzaOrder newOrder) {
        //If it doenst exist, findById, throws exception
        PizzaOrder orderFound = findById(id);
        
            orderFound.setCustomerName(newOrder.getCustomerName());
            orderFound.setPizzaType(newOrder.getPizzaType());
            orderFound.setQuantity(newOrder.getQuantity());

            orderFound.setPrice(getPriceFromMenu(newOrder.getPizzaType()) * newOrder.getQuantity());
        
        return orderFound;
    }

    // D
    public void deleteOrder(String id) {
        orderRepository.deleteOrder(id);
    }

    public PizzaOrder findById(String id) {
        PizzaOrder order = orderRepository.findById(id);
        if (order == null) {
            throw new NoSuchElementException("We couldnt find the order with ID : " + id);

        }
        return order;
    }

    public Double getPriceFromMenu(String typePizza) {
        Double pricePizza = MENU.get(typePizza);
        if (pricePizza == null) {
            throw new IllegalArgumentException("Sorry, We do not have that pizza " + typePizza);
        } else {
            return pricePizza;
        }

    }
}
