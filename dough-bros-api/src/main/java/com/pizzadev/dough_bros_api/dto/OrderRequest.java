package com.pizzadev.dough_bros_api.dto;

import java.util.List;

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
    
    @NotBlank(message = "The order must have at least one pizza")
    List<Long> pizzaIds;

    
    @NotNull
    private Long customerId;
    


}
