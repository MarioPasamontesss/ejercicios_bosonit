package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturasInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.input.StudentInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturaIdInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaStudentOutputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.StudentOutputDto;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioAsignatura;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioProfesor;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceStudent implements com.ejercicios.DB1jpa.aplication.services.ServiceStudentInterface {

    @Autowired
    RepositorioStudent repositorioStudent;
    @Autowired
    RepositorioAsignatura repositorioAsignatura;
    @Autowired
    RepositorioProfesor repositorioProfesor;

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentID) throws NotFExceptions{
        StudentEntity studentEntity = new StudentEntity(studentID);
        for(ProfesorEntity profesor : repositorioProfesor.findAll()){
            if(profesor.getPersona().getId_persona() == studentEntity.getPersona().getId_persona()){
                throw new NotFExceptions("No sepuede agregar, ya pertenece este id a un profesor");
            }
        }
        repositorioStudent.save(studentEntity);
        StudentOutputDto studentOutputDto = new StudentOutputDto(studentEntity);
        return studentOutputDto;
    }
    @Override
    public StudentOutputDto updateStudent(StudentInputDto studentID, String id) throws NotFExceptions{
        boolean existe = repositorioStudent.existsById(id);
        if(existe){
            repositorioStudent.deleteById(id);
            StudentEntity studentEntity = new StudentEntity(studentID);
            repositorioStudent.save(studentEntity);
            StudentOutputDto studentOutputDto = new StudentOutputDto(studentEntity);
            return studentOutputDto;
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public void deleteStudent(String id) throws NotFExceptions{
        boolean existe = repositorioStudent.existsById(id);
        if(existe){
            repositorioStudent.deleteById(id);
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public List<StudentOutputDto> showAll(){
        List<StudentOutputDto>  studentOutputDtoList = new ArrayList<>();
        for (StudentEntity studentEntity : repositorioStudent.findAll()){
            studentOutputDtoList.add(new StudentOutputDto(studentEntity));
        }
        return studentOutputDtoList;
    }
    @Override
    public StudentOutputDto findIdStudent(String id) throws Exception {
            StudentEntity studentEntity = repositorioStudent.findById(id).orElseThrow(() -> new Exception("Error"));
            StudentOutputDto studentOutputDto = new StudentOutputDto(studentEntity);
            return studentOutputDto;
    }
    @Override
    public PersonaStudentOutputDto findIdStudentFull(String id) throws Exception {
        StudentEntity studentEntity = repositorioStudent.findById(id).orElseThrow(() -> new Exception("Error"));
        Persona personaEntity = studentEntity.getPersona();
        PersonaStudentOutputDto personaStudentOutputDto = new PersonaStudentOutputDto(personaEntity, studentEntity);
        return personaStudentOutputDto;
    }
    @Override
    public void modificarListAsignaturas(List<AsignaturaIdInputDto> listaAsisg, String id){
        try {
            StudentEntity student = repositorioStudent.findById(id).orElseThrow(() -> new Exception("Error"));

            for(AsignaturaIdInputDto a : listaAsisg){
                for(AsignaturaEntity asignaturaEntity : repositorioAsignatura.findAll()){
                    if(a.getId_asignatura().equals(asignaturaEntity.getId_asignatura())){
                        student.getAsignaturas().add(asignaturaEntity);
                    }else{
                        throw new NotFExceptions("Error no existen");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void designarListAsignaturas(List<AsignaturaIdInputDto> listaAsisg, String id){
        try {
            StudentEntity student = repositorioStudent.findById(id).orElseThrow(() -> new Exception("Error"));

            for(AsignaturaIdInputDto a : listaAsisg){
                for(AsignaturaEntity asignaturaEntity : repositorioAsignatura.findAll()){
                    if(a.getId_asignatura().equals(asignaturaEntity.getId_asignatura())){
                        student.getAsignaturas().remove(asignaturaEntity);
                    }else{
                        throw new NotFExceptions("Error no existen");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
