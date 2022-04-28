package com.ejercicios.DB1jpa.infraestructure.controler.dto.input;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.Date;

@Data
@Entity
public class PersonaInputDto {
    @Id
    @GeneratedValue
    @JoinColumn(name = "Identificador")
    int id_persona;
    @NotNull
    String usuario;
    @NotNull
    String password;
    @NotNull
    String name;
    String surname;
    @NotNull
    String company_email;
    @NotNull
    String personal_email;
    @NotNull
    String city;
    @NotNull
    boolean active;
    @NotNull
    Date create_date;
    String imagen_url;
    Date termination_date;
}
