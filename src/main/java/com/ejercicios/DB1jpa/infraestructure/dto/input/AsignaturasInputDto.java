package com.ejercicios.DB1jpa.infraestructure.dto.input;


import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AsignaturasInputDto {
    String id_asignatura;
    ProfesorEntity profesor;
    StudentEntity student;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
}
