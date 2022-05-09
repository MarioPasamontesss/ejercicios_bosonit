package com.ejercicios.DB1jpa.infraestructure.dto.output;

import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AsignaturasOutputDto {
    String id_asignatura;
    String profesor;
    String student;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;


    public AsignaturasOutputDto(AsignaturaEntity asignaturaEntity){
        setId_asignatura(asignaturaEntity.getId_asignatura());
        setProfesor(asignaturaEntity.getProfesor());
        setStudent(asignaturaEntity.getStudent());
        setAsignatura(asignaturaEntity.getAsignatura());
        setComents(asignaturaEntity.getComents());
        setInitial_date(asignaturaEntity.getInitial_date());
        setFinish_date(asignaturaEntity.getFinish_date());
    }
}
