package com.pizzadev.dough_bros_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pizzadev.dough_bros_api.dto.OrderRequest;
import com.pizzadev.dough_bros_api.model.OrderStatus;
import com.pizzadev.dough_bros_api.model.PizzaOrder;
import com.pizzadev.dough_bros_api.service.OrderService;
import com.pizzadev.dough_bros_api.service.OrderServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController // Anotación controlador, componente Spring maneja peticiones HTML
                // El valor de retorno de los metodos los convierte automaticamente en JSON
@RequiredArgsConstructor // Constructor necesario para nuestro OrderService
public class OrderController {

    // Final para que sea inmutable
    private final OrderService orderService;

    @Operation(summary = "List all the orders", description = "List all the orders and it sends 200 once its done")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping("/api/orders")
    public ResponseEntity<List<PizzaOrder>> listAll() {
        return ResponseEntity.ok(orderService.findAll());

    }

    @Operation(summary = "List the order by id")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Not found")
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<PizzaOrder> orderById(@PathVariable Long id) {
        return orderService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        // findbyid nos da el optional, map abre la caja digamos y dice que itere sobre
        // lo que hay en la caja
        // si hay algo significa que es bueno y manda una señal de ok
        // si no hay nada, usamos orelseget y mandamos aviso de que no hay nada
        // usamos lambda, java moderno
        // Method reference o OrderFound -> ResponseEntity.ok, ResponseEntity :: ok
        // return orderService.findById(id).map(orderFound ->
        // ResponseEntity.ok(orderFound)).orElseGet(()->
        // ResponseEntity.notFound().build());
    }

    @Operation(summary = "Register a new order")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("/api/orders/submit")
    public ResponseEntity<PizzaOrder> submitOrder(@Valid @RequestBody OrderRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
        // Creamos post request, queremos devolver un responseEntity de pizzaOrder
        // Validacion, y necesitamos mandarle algo @RequestBody con nuestro dto
        // Devolver ResponseEntity con el status de creado y en el cuerpo creamos la
        // accion con el service

    }

    @Operation(summary = "Update the order")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PutMapping("/api/orders/{id}")
    public ResponseEntity<PizzaOrder> update(@PathVariable Long id, @Valid @RequestBody OrderRequest request) {
        // Creamos el put request, queremos devolver un ResponseEntity con un de
        // PizzaOrder, por lo que tenemos que crear uno
        // Queremos devolver status tambien de que se ha realizado o ha sido un bad
        // request(id no encontrado)
        // Usamos update que ya busca el id , nos dara el optional
        // Map para filtrar si hay algo en la caja
        // Si hay algo, tenemos que actualizarlo y mandar status ok
        // si no hay nada mandamos un not found

        return orderService.update(id, request).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Delete an order")
    @ApiResponse(responseCode = "204", description = "Successful operation, no content back")
    @ApiResponse(responseCode = "404", description = "Not found id")
    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Delete : usa findById, y es un optional
        // Usamos map para abrir la caja, si hay algo, lo borramos y devolvemos un
        // status de ok
        // Si no hay nada, devolvemos status de notfound
        // Recordar especificar que es Void

        return orderService.delete(id).map(order -> ResponseEntity.noContent().<Void>build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Change order´s status from Received to Preparing to Ready and finally Delivered")
    @ApiResponse(responseCode = "200", description = "Order´s status updated successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Admin role needed for this operation")
    @PatchMapping("/api/orders/{id}/next")
    public ResponseEntity<PizzaOrder> progression(@PathVariable Long id) {
        // Llamamos al service, y el metodo ya llama a findById
        // Nos da el optional, abrimos la caja con map
        // Si hay algo, lo envolvemos con un status ok,
        // Si no hay nada, devolvemos un not found

        return orderService.statusProgress(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

}