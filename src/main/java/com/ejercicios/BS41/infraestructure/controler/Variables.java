package com.ejercicios.BS41.infraestructure.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Variables {
    /*@Value("${URL}")
    String url;
    @Value("${password}")
    String password;

    @GetMapping("/parametros")
    public String getVariables(){
        return "URL:" + url + " password: " + password;
    }
*/
}
