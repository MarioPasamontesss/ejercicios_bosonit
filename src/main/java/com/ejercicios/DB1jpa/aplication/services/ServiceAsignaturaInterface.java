package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturasInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.AsignaturasOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ServiceAsignaturaInterface {
    AsignaturasOutputDto addAsignatura(AsignaturasInputDto asignaturasInputDto);
    AsignaturasOutputDto updateAsignatura(AsignaturasInputDto asignaturasInputDto, String id) throws NotFExceptions;
    void deleteAsignatura(String id) throws NotFExceptions;
    List<AsignaturasOutputDto> findAll();
    AsignaturasOutputDto findIdAsignatura(String id);
    //List<AsignaturaEntity> finStudentListAsignaturas(String id);
}
