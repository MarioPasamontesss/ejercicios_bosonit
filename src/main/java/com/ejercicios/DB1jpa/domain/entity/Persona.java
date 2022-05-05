package com.ejercicios.DB1jpa.domain.entity;

import com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator;
import com.ejercicios.DB1jpa.infraestructure.dto.input.PersonaInputDto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE/*, generator = "ausencias_seq"*/)
    /*@GenericGenerator(
            name = "ausencias_seq",
            strategy = "com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })*/
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
