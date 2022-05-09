package com.ejercicios.BS4.infraestructure.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@RestController
//@PropertySource("classpath: application.properties")
@ConfigurationProperties(prefix = "my")
//@ConfigurationProperties("application.properties")
public class ControladorBS4 {

    @Value("${VAR1}")
    String VAR1;
    @Value("${My.VAR2}")
    String VAR2;
    @Value("${VAR3}")
    String VAR3;


    @GetMapping("/var")
    public String mostrarValores(){
        return "El valor de v1 es " + VAR1 + ", el valor de v2 es " + VAR2;
    }
    @GetMapping("/var2")
    public String mostrarValoresVar3(){
        if(VAR3 == null) {
            return "El valor es null";
        }else{
            return "";
        }
    }

}
