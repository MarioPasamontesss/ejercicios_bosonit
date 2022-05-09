package com.ejercicios.DB1jpa.domain.entity;

import com.ejercicios.DB1jpa.infraestructure.dto.input.ProfesorInputDto;
import com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@Slf4j
@Entity
@NoArgsConstructor
@Table(name = "Profesores")
public class ProfesorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ausencias_seq")
    @GenericGenerator(
            name = "ausencias_seq",
            strategy = "com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            })
    String id_profesor;
    @OneToOne
    @JoinColumn(name = "ID_PERSONA")
    Persona persona;
    String coments;
    @Column(nullable = false)
    String branch;


    public ProfesorEntity(ProfesorInputDto profesor){
        setId_profesor(profesor.getId_profesor());
        Persona persona1 = new Persona();
        persona1.setId_persona(profesor.getId_persona());
        setPersona(persona1);
        setComents(profesor.getComents());
        setBranch(profesor.getBranch());
    }
}
