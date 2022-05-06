package com.ejercicios.BS3.domain.entity;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class claseSecundaria {
    @Bean
    CommandLineRunner ejecutame1() {
        return p ->
        {
            System.out.println("Hola desde clase secundaria");
        };

    }
}