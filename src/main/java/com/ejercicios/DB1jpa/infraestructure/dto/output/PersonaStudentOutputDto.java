package com.ejercicios.DB1jpa.infraestructure.dto.output;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PersonaStudentOutputDto{
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
        String id_student;
        int num_hours_week;
        String coments;


    public PersonaStudentOutputDto(Persona persona, StudentEntity studentEntity){
        this.id_persona = persona.getId_persona();
        this.usuario = persona.getUsuario();
        this.password = persona.getPassword();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getCompany_email();
        this.personal_email = persona.getPersonal_email();
        this.city = persona.getCity();
        this.active = persona.isActive();
        this.create_date = persona.getCreate_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getTermination_date();
        this.id_student = studentEntity.getId_student();
        this.num_hours_week = studentEntity.getNum_hours_week();
        this.coments = studentEntity.getComents();
    }
}
