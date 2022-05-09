package com.ejercicios.BS.infraestructure.controler;

import com.ejercicios.BS.domain.entity.Persona;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PersonaController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Persona person(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Persona(String.format(template, name), 22);
    }
}
