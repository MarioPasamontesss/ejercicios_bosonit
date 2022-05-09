package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.aplication.services.ServicePersonInterface;
import com.ejercicios.DB1jpa.infraestructure.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaOutputDto;
import com.ejercicios.DB1jpa.aplication.services.ServicePerson;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/BS1/controlerPersona")
public class ControladorPersona {

    @Autowired
    ServicePersonInterface servicePerson;

    public ControladorPersona(ServicePersonInterface servicePerson) {
        this.servicePerson = servicePerson;
    }

    @PostMapping("/personadd")
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
    public void deletePerson(@PathVariable int id) {
        servicePerson.deletePerson(id);
    }
    @PutMapping("/updatePerson/{id}")
    public PersonaOutputDto updatePerson(@RequestBody PersonaInputDto personaInputDto, @RequestParam int id) {
        return servicePerson.updatePerson(personaInputDto, id);
    }
    /*@GetMapping("/findById/{id}")
    public PersonaOutputDto findPersonId(@PathVariable int id) {
        PersonaOutputDto personaOutputDto;
        try {
            return personaOutputDto = servicePerson.findByIdPerson(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
    @GetMapping("/findById/{id}")
    public ResponseEntity findPersonIdOutputType(@PathVariable int id, @RequestParam(value = "ouputType") String ouputType) {
        try {
            return ResponseEntity.ok(servicePerson.findByIdPersonOutputType(id, ouputType));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Person is null");
        }
    }
   /* @GetMapping("/findByUser/{user}")
   public List<PersonaOutputDto> findPersonUser(@RequestParam String name){
        try {
            return servicePerson.findNamePerson(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } */
    @GetMapping("/findByUser/{user}")
    public ResponseEntity findPersonUserOutPut(@RequestParam String name, @RequestParam(value = "ouputType") String ouputType){
        try {
            return ResponseEntity.ok(servicePerson.findNamePersonoutputType(name, ouputType));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/findAll")
    public List<PersonaOutputDto> findPersonas(){
         return servicePerson.findAll();
    }

    @GetMapping("/findAllOutput")
    public List<PersonaOutputDto> findAlloutputType() {
        return servicePerson.findAlloutputType();
    }

    /*@RestController("profesor/{id}")
    public ProfesorOutputDto getProfesor(@PathVariable int id){
    }*/
}
