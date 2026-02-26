package com.pizzadev.dough_bros_api.dto;

import com.pizzadev.dough_bros_api.model.Customer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull(message = "Please Introduce the amount: ")
    @Min(value = 1,message = "Minimum 1")
    private Integer quantity;
    @NotNull
    private Long customerId;
    @NotBlank(message = "Please introduce your prefered pizza")
    private String pizzaType;


}
