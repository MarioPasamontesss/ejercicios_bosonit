package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.aplication.services.ServicePersonInterface;
import com.ejercicios.DB1jpa.infraestructure.controler.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.controler.dto.output.PersonaOutputDto;
import com.ejercicios.DB1jpa.aplication.services.ServicePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/controler")
public class Controlador {

    @Autowired
    ServicePersonInterface servicePerson;

    public Controlador(ServicePerson servicePerson) {
        this.servicePerson = servicePerson;
    }

    @PostMapping("/useradd")
    public PersonaOutputDto addPerson(@RequestBody PersonaInputDto persona){
        PersonaOutputDto personaOD = null;
        try {
            personaOD = servicePerson.addPersona(persona);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return personaOD;
    }
    @DeleteMapping("/delete/{id}")
    public void deletePerson(int id){
        servicePerson.deletePerson(id);
    }
    @PutMapping("{id}")
    public PersonaOutputDto updatePerson(@RequestBody PersonaInputDto personaInputDto, @RequestParam int id){
        return servicePerson.updatePerson(personaInputDto, id);
    }
    @GetMapping("{id}")
    public PersonaOutputDto findPersonId(@PathVariable int id){
        try {
            return servicePerson.findByIdPerson(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("{user}")
   public List<PersonaOutputDto> findPersonUser(@RequestParam String name){
        try {
            return servicePerson.findNamePerson(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
