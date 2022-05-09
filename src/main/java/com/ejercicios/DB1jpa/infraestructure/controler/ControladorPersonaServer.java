package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.ProfesorInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BS1/controlerClient")
public class ControladorPersonaServer {

    /*@GetMapping("{httpCode}")
    ResponseEntity<ProfesorOutputDto> getHttpCode(@PathVariable ProfesorInputDto profesorInputDto)
    {
        ProfesorEntity profesor = new ProfesorEntity(profesorInputDto);
        System.out.println("En server. Devolvere: "+ profesor);
        return ResponseEntity.ok(new ProfesorOutputDto(profesor));
    }*/
}
