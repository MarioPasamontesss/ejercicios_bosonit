package com.ejercicios.DB1jpa.domain.entity;

import com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator;
import com.ejercicios.DB1jpa.infraestructure.dto.input.PersonaInputDto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
@Entity
@NoArgsConstructor
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id_persona;
    @NotNull
    @Column(nullable = false)
    String usuario;
    @NotNull
    @Column(nullable = false)
    String password;
    @NotNull
    @Column(nullable = false)
    String name;
    String surname;
    @NotNull
    @Column(nullable = false)
    String company_email;
    @NotNull
    @Column(nullable = false)
    String personal_email;
    @NotNull
    @Column(nullable = false)
    String city;
    @NotNull
    @Column(nullable = false)
    boolean active;
    @NotNull
    @Column(nullable = false)
    Date create_date;
    String imagen_url;
    Date termination_date;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    StudentEntity estudiante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    ProfesorEntity profesor;
     */
    public Persona(PersonaInputDto personaInputDto){
        setId_persona(personaInputDto.id_persona());
        setName(personaInputDto.name());
        setUsuario(personaInputDto.usuario());
        setPassword(personaInputDto.password());
        setSurname(personaInputDto.surname());
        setCompany_email(personaInputDto.company_email());
        setPersonal_email(personaInputDto.personal_email());
        setCity(personaInputDto.city());
        setActive(personaInputDto.active());
        setCreate_date(personaInputDto.create_date());
        setImagen_url(personaInputDto.imagen_url());
        setTermination_date(personaInputDto.termination_date());
    }
    public Persona(int id_persona) {
        this.id_persona = id_persona;
    }

}
