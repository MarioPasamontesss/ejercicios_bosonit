package com.ejercicios.BS41.infraestructure.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
//@PropertySource("miconfiguracion.properties")
public class Controlador {
    @Value("${valor1}")
    String valor1;
    @Value("${valor2}")
    String valor2;

    @GetMapping("/miconfig")
    public String getVariables(){
        return   valor1 +  valor2;
    }



}
