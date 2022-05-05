package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.infraestructure.dto.input.StudentInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturaIdInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaStudentOutputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.StudentOutputDto;

import java.util.List;

public interface ServiceStudentInterface {
    StudentOutputDto addStudent(StudentInputDto studentID) throws NotFExceptions;
    StudentOutputDto updateStudent(StudentInputDto studentID, String id) throws NotFExceptions;
    void deleteStudent(String id) throws NotFExceptions;
    List<StudentOutputDto> showAll();
    StudentOutputDto findIdStudent(String id) throws Exception;
    PersonaStudentOutputDto findIdStudentFull(String id) throws Exception;
    void modificarListAsignaturas(List<AsignaturaIdInputDto> listaAsisg, String id);
    void designarListAsignaturas(List<AsignaturaIdInputDto> listaAsisg, String id);
}
