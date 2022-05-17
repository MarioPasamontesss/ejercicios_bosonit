package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.ProfesorInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
//import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

public interface ServiceProfesorInterface {

    ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) throws NotFExceptions;
    void deleteProfesor(String id) throws NotFExceptions;
    ProfesorOutputDto updateProfesor(ProfesorInputDto profesorInputDto ,String id) throws NotFExceptions;
    List<ProfesorOutputDto> findAll();
    ProfesorOutputDto findIdProfesor(String id);
}
