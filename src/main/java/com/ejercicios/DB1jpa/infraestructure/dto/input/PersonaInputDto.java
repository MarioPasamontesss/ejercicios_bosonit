package com.ejercicios.DB1jpa.infraestructure.dto.input;

import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaInputDto {
    int id_persona;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date create_date;
    String imagen_url;
    Date termination_date;
    StudentEntity id_estudiante;
    ProfesorEntity id_profesor;
}
