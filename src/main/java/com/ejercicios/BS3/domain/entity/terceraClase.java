package com.ejercicios.BS3.domain.entity;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
public class terceraClase {

    @Bean
    @PostMapping
    CommandLineRunner ejecutame2() {
        return p ->
        {
            System.out.println("Hola desde tercera clase");
        };

    }
}
