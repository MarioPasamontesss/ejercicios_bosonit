package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.aplication.services.ServicePersonInterface;
import com.ejercicios.DB1jpa.aplication.services.ServiceProfesorInterface;
import com.ejercicios.DB1jpa.aplication.services.iFeignClient;
import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaOutputDto;
import com.ejercicios.DB1jpa.aplication.services.ServicePerson;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/BS1/controlerPersona")
public class ControladorPersona {

    @Autowired
    ServicePersonInterface servicePerson;
    @Autowired
    iFeignClient iFeignClient;

    @Autowired
    EntityManager em;
    
    public ControladorPersona(ServicePersonInterface servicePerson) {
        this.servicePerson = servicePerson;
    }


    @PostMapping("/personadd")
    @CrossOrigin(origins = "https://codepen.io/")
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
    @CrossOrigin(origins = "http://localhost:8080")
    public List<PersonaOutputDto> findPersonas(){
         return servicePerson.findAll();
    }

    /*@GetMapping("/findAllOutput")
    public List<PersonaOutputDto> findAlloutputType() {
        return servicePerson.findAlloutputType();
    }*/

    @GetMapping("/Profesortemplate/{code}")
    ResponseEntity<ProfesorOutputDto> callGetProfesor(@PathVariable int code){
        ResponseEntity<ProfesorOutputDto> responseEntity= new RestTemplate().getForEntity("http://localhost:8081/BS1/controlerPersona/" + code, ProfesorOutputDto.class);
        return ResponseEntity.ok(responseEntity.getBody());
    }
    @GetMapping("/ProfesorFeign/{id}")
    ResponseEntity<ProfesorOutputDto> callFeignProfesor(@PathVariable int id){
    return iFeignClient.getProfesor(id);
    }

    /*@GetMapping("/get")
    public ResponseEntity<List<Persona>> getData(@RequestParam(required=false,name="user") String user,
                                                 @RequestParam(required=false,value="name") String name,
                                                 @RequestParam(required=false,value="surname") String surname,
                                                 @RequestParam(required=false) @DateTimeFormat(pattern="dd-MM-yyyy") Date createdDate, @RequestParam(required=false) String dateCondition			)
    {
        HashMap<String, Object> data=new HashMap<>();

        if (user!=null)
            data.put("id",user);
        if (name!=null)
            data.put("name",name);
        if (surname!=null)
            data.put("address",surname);
        if (!dateCondition.equals("greater") && dateCondition.equals("less")){
            dateCondition = "less";
        }
        if (dateCondition.equals("greater") && !dateCondition.equals("less"))
            dateCondition="greater";
        if (createdDate!=null)
        {
            data.put("created",createdDate);
            data.put("dateCondition",dateCondition);
        }

        return servicePerson.getCreate_date(data);

    }*/
}
