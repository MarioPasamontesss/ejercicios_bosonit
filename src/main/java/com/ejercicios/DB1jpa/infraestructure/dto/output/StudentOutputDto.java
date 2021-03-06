package com.ejercicios.DB1jpa.infraestructure.dto.output;

import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StudentOutputDto {
    String id_student;
    int persona;
    int num_hours_week;
    String coments;
    String id_profesor;
    String branch;
    List<AsignaturaEntity> asignaturas;

    public StudentOutputDto(StudentEntity studentEntity){
        setId_student(studentEntity.getId_student());
        setPersona(studentEntity.getPersona().getId_persona());
        setNum_hours_week(studentEntity.getNum_hours_week());
        setComents(studentEntity.getComents());
        setId_profesor(studentEntity.getProfesor().getId_profesor());
        setBranch(studentEntity.getBranch());
        setAsignaturas(studentEntity.getAsignaturas());
    }

}
