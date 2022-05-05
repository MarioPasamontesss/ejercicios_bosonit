package com.ejercicios.DB1jpa.infraestructure.dto.input;

import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.domain.entity.Persona;
import lombok.Data;

import java.util.List;

@Data
public class StudentInputDto {
    String id_student;
    Persona persona;
    int num_hours_week;
    String coments;
    String id_profesor;
    String branch;
    List<AsignaturaEntity> asignaturas;
}
