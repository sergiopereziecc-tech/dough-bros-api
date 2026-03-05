package com.pizzadev.dough_bros_api.dto;

import java.util.List;

import com.pizzadev.dough_bros_api.model.Customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    
    @Schema(description = "List to store all the ids from the pizza order")
    @NotEmpty(message = "The order must have at least one pizza")
    List<Long> pizzaIds;

    
    @Schema(description = "Id from the ordering customer", example = "1")
    @NotNull
    private Long customerId;
    


}
