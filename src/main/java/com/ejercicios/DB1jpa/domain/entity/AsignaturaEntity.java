package com.ejercicios.DB1jpa.domain.entity;

import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturasInputDto;
import com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@Entity
@Table(name = "Asignaturas")
@NoArgsConstructor
public class AsignaturaEntity {

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
    String id_asignatura;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id")
    ProfesorEntity profesor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student")
    StudentEntity student;
    String asignatura;
    String coments;
    @Column(nullable = false)
    Date initial_date;
    Date finish_date;



    public AsignaturaEntity(AsignaturasInputDto asignatura){
        setId_asignatura(asignatura.getId_asignatura());
        setProfesor(asignatura.getProfesor());
        setStudent(asignatura.getStudent());
        setAsignatura(asignatura.getAsignatura());
        setComents(asignatura.getComents());
        setInitial_date(asignatura.getInitial_date());
        setFinish_date(asignatura.getFinish_date());
    }
}
