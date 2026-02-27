package com.pizzadev.dough_bros_api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pizzadev.dough_bros_api.dto.OrderRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String pizzaType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int quantity;
    private double price;

    @ManyToMany
    @JoinTable(name = "order_pizzas",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizzas = new ArrayList<>();

    public PizzaOrder(OrderRequest request){
        
        
        this.pizzaType = request.getPizzaType();
        this.quantity = request.getQuantity();

        //Inicial State
        this.status = OrderStatus.RECEIVED;

    }

    public void updateFromRequest(OrderRequest request, double newPrice){
        
        this.pizzaType = request.getPizzaType();
        this.quantity = request.getQuantity();
        this.price = newPrice;
    }

    
    
}
