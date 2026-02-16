package com.pizzadev.dough_bros_api.model;

import java.util.UUID;

import com.pizzadev.dough_bros_api.dto.OrderRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;
    @NotBlank(message = "Pizza type is a must")
    private String pizzaType;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Min(value = 1, message = "Minimun quantity is 1")
    private int quantity;
    private double price;

    public PizzaOrder(OrderRequest request){
        //Customer data
        this.customerName = request.getCustomerName();
        this.pizzaType = request.getPizzaType();
        this.quantity = request.getQuantity();

        //Inicial State
        this.status = OrderStatus.RECEIVED;

    }

    public void updateFromRequest(OrderRequest request, double newPrice){
        this.customerName = request.getCustomerName();
        this.pizzaType = request.getPizzaType();
        this.quantity = request.getQuantity();
        this.price = newPrice;
    }

    
    
}
