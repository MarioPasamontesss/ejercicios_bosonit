package com.ejercicios.DB1jpa.persona;

import com.ejercicios.DB1jpa.persona.dto.PersonaInputDto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.Date;

@Data
@Slf4j
@Entity
public class Persona {

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

    public Persona(){

    }
    public Persona(PersonaInputDto personaInputDto){
        setId_persona(personaInputDto.getId_persona());
        setName(personaInputDto.getName());
        setUsuario(personaInputDto.getUsuario());
        setPassword(personaInputDto.getPassword());
        setSurname(personaInputDto.getSurname());
        setCompany_email(personaInputDto.getCompany_email());
        setPersonal_email(personaInputDto.getPersonal_email());
        setCity(personaInputDto.getCity());
        setActive(personaInputDto.isActive());
        setCreate_date(personaInputDto.getCreate_date());
        setImagen_url(personaInputDto.getImagen_url());
        setTermination_date(personaInputDto.getTermination_date());
    }

}
