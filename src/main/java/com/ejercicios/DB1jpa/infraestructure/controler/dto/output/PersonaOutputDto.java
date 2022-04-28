package com.ejercicios.DB1jpa.infraestructure.controler.dto.output;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.Date;

@Data
@Entity
public class PersonaOutputDto {
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

    public PersonaOutputDto(){

    }
    public PersonaOutputDto(Persona persona){
        setId_persona(persona.getId_persona());
        setUsuario(persona.getUsuario());
        setPassword(persona.getPassword());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCity(persona.getCity());
        setActive(persona.isActive());
        setCreate_date(persona.getCreate_date());
        setImagen_url(persona.getImagen_url());
        setTermination_date(persona.getTermination_date());
    }
}
