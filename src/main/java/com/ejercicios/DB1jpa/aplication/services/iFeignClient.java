package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feign", url = "http://localhost:8081/BS1/controlerProfesor/server/profesorFeign")
public interface iFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<ProfesorOutputDto> getProfesor(@PathVariable int id);
}
