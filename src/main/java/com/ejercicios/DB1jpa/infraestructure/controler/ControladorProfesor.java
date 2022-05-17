package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.aplication.services.ServicePersonInterface;
import com.ejercicios.DB1jpa.aplication.services.ServiceProfesorInterface;
import com.ejercicios.DB1jpa.aplication.services.iFeignClient;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.ProfesorInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@FeignClient
@RequestMapping("/BS1/controlerProfesor")
public class ControladorProfesor {

    @Autowired
    ServiceProfesorInterface serviceProfesorInterface;
    @Autowired
    ServicePersonInterface servicePersonInterface;


    public ControladorProfesor(ServiceProfesorInterface serviceProfesorInterface) {
        this.serviceProfesorInterface = serviceProfesorInterface;
    }
    @PostMapping("/addProfesor")
    public ProfesorOutputDto addProfesor(@RequestBody ProfesorInputDto profesorInputDto){
        try {
            return serviceProfesorInterface.addProfesor(profesorInputDto);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/updateProfesor/{id}")
    public ProfesorOutputDto updateProfesor(@RequestBody ProfesorInputDto profesorInputDto, @RequestParam String id){
        try {
            return serviceProfesorInterface.updateProfesor(profesorInputDto, id);
        }catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProfesor(@PathVariable String id){
        try {
            serviceProfesorInterface.deleteProfesor(id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/findAll")
    public List<ProfesorOutputDto> findAll(){
        try {
            return serviceProfesorInterface.findAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("findById/{id}")
    public ProfesorOutputDto findById(@PathVariable String id){
        try {
            return serviceProfesorInterface.findIdProfesor(id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/server/profesor")
    ResponseEntity<ProfesorOutputDto> getProfesor(@PathVariable String id)
    {
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(servicePersonInterface.findProfesor(id));
        System.out.println("En server. Devolvere: "+ profesorOutputDto);
        return ResponseEntity.status(200).body(profesorOutputDto);
    }
    @GetMapping("/server/profesorFeign")
    ResponseEntity<ProfesorOutputDto> feignProfesor(@PathVariable String id)
    {
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(servicePersonInterface.findProfesor(id));
        System.out.println("En server. Devolvere: "+ profesorOutputDto);
        return ResponseEntity.status(200).body(profesorOutputDto);
    }
}
