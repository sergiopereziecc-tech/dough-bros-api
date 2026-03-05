package com.pizzadev.dough_bros_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Pizzeria API",
version = "1.0",
description = "Sistema de gestión de pedidos con seguridad basada en roles(Cliente/Admin)"
					
))
public class DoughBrosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoughBrosApiApplication.class, args);
	}

}
