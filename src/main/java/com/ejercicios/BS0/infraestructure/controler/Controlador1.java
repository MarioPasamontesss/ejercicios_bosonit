package com.ejercicios.BS0.infraestructure.controler;


import com.ejercicios.BS0.domain.entity.Persona;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class Controlador1 {

    ///////////////////EJERCICIO1///////////////////
    @GetMapping("/user")
    public String getUser(){
        return "Mario";
    }

    @PostMapping("/useradd")
    public Persona getUser1(@RequestBody Persona pe){
        pe = new Persona(pe.getNombre(), pe.getCiudad(), pe.getEdad() + 1);
        return pe;
    }

}
