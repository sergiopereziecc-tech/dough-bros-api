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


    
    public PizzaOrder(String customerName, String pizzaType, OrderStatus status, double price, int quantity) {
        
        this.customerName = customerName;
        this.pizzaType = pizzaType;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
    }
    public PizzaOrder(OrderRequest request){
        //Customer data
        this.customerName = request.getCustomerName();
        this.pizzaType = request.getPizzaType();
        this.quantity = request.getQuantity();

        //Inicial State
        this.status = OrderStatus.RECEIVED;

    }

    //Empty constructor needed to initiate objetcs before filling them with data
    public PizzaOrder(){

    }
    

    //No hay setId, no queremos cambios en el ID

    public String getId() {
        return this.id;
    }

    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPizzaType() {
        return this.pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
