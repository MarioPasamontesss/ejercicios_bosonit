package com.ejercicios.BS3.domain.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class claseInicial {

    @PostConstruct
    public void primeraEjecucion(){
        System.out.println("Hola desde clase inicial");
    }
}
