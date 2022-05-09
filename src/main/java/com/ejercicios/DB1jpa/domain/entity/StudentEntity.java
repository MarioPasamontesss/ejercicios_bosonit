package com.ejercicios.DB1jpa.domain.entity;

import com.ejercicios.DB1jpa.infraestructure.dto.input.StudentInputDto;
import com.ejercicios.DB1jpa.sequences.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Data
@Slf4j
@Entity
@NoArgsConstructor
@Table(name = "Estudiantes")
public class StudentEntity {

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
    String id_student;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_student")
    int persona;

    @Column(name = "horas_semanales", nullable = false)
    int num_hours_week;
    @Column(name = "comentarios")
    String coments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    String profesor;
    @Column(name = "rama")
    String branch;
    @OneToMany
    List<AsignaturaEntity> asignaturas;



    public StudentEntity(StudentInputDto student){
        setId_student(student.getId_student());
        setPersona(student.getPersona());
        setNum_hours_week(student.getNum_hours_week());
        setComents(student.getComents());
        setProfesor(student.getId_profesor());
        setBranch(student.getBranch());
        setAsignaturas(student.getAsignaturas());
    }
}
