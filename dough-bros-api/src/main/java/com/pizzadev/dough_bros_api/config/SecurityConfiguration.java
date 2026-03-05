package com.pizzadev.dough_bros_api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    public InMemoryUserDetailsManager usuarios(){
        UserDetails customer = User.withUsername("client").password("{noop}1234").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1234").roles("ADMIN").build();
        
        return new InMemoryUserDetailsManager(customer,admin);
    } 
    
    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception{

        return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
            auth.requestMatchers("/h2-console/**").permitAll();
            auth.requestMatchers(HttpMethod.PATCH, "/api/orders/**/next").hasRole("ADMIN");
            auth.requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll();
            auth.anyRequest().authenticated();
        })
        .headers(headers -> headers.frameOptions(f-> f.disable()))
        .httpBasic(Customizer.withDefaults())
        .build();
    }
    
}
