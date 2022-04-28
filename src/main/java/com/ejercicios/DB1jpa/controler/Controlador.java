package com.ejercicios.DB1jpa.controler;

import com.ejercicios.DB1jpa.persona.Persona;
import com.ejercicios.DB1jpa.persona.dto.PersonaInputDto;
import com.ejercicios.DB1jpa.persona.dto.PersonaOutputDto;
import com.ejercicios.DB1jpa.services.ServicePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/controler")
public class Controlador {

    @Autowired
    ServicePerson servicePerson;

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
    @DeleteMapping("{id}")
    public void deletePerson(int id){
        servicePerson.deletePerson(id);
    }
    @PutMapping("/putP")
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
