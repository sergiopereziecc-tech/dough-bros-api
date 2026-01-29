package com.pizzadev.dough_bros_api.model;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class PizzaOrder {
    private String id;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;
    @NotBlank(message = "Pizza type is a must")
    private String pizzaType;

    private OrderStatus status;
    private double price;


    //No id parameter, because it is randomly assigned once the order is created
    public PizzaOrder(String customerName, String pizzaType, OrderStatus status, double price) {
        this.id = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.pizzaType = pizzaType;
        this.status = status;
        this.price = price;
    }

    //Empty constructor needed to iniliate objetcs before filling them with data
    public PizzaOrder(){

    }
    

    //No hay setId, no queremos cambios en el ID

    public String getId() {
        return this.id;
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
