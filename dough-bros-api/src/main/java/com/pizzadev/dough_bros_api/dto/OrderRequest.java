package com.pizzadev.dough_bros_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {
    @NotNull(message = "Please Introduce the amount: ")
    @Min(value = 1,message = "Minimum 1")
    private Integer quantity;
    @NotBlank(message = "Please Introduce your name")
    private String customerName;
    @NotBlank(message = "Please introduce your prefered pizza")
    private String pizzaType;


    public OrderRequest(){
        
    }


    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
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



}
