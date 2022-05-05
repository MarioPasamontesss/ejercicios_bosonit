package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaOutputDto;
import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaProfesorOutputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaStudentOutputDto;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioPersona;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioProfesor;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePerson implements ServicePersonInterface{

    @Autowired
    RepositorioPersona repositorioPersona;
    @Autowired
    RepositorioProfesor repositorioProfesor;
    @Autowired
    RepositorioStudent repositorioStudent;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaID) throws NotFExceptions{
        if(personaID.getUsuario().length() < 6 || personaID.getUsuario().length()>10){
            throw new NotFExceptions("El usuario debe contener entre 6 y 10 caracteres");
        }else{
            Persona person = new Persona(personaID);
            repositorioPersona.save(person);
            PersonaOutputDto personaOD = new PersonaOutputDto(person);
            return personaOD;
        }
    }
    @Override
    public void deletePerson(int id) throws NotFExceptions{
        boolean existe = repositorioPersona.existsById(id);
        if(existe){
            repositorioPersona.deleteById(id);
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
        }
    @Override
    public PersonaOutputDto updatePerson(PersonaInputDto personaInputDto, int id){
        boolean existe = repositorioPersona.existsById(id);
        if(existe){
            if(personaInputDto.getUsuario().length() < 6 || personaInputDto.getUsuario().length()>10) {
                throw new NotFExceptions("El usuario debe contener entre 6 y 10 caracteres");
            }else {
                repositorioPersona.deleteById(id);
                Persona persona = new Persona(personaInputDto);
                repositorioPersona.save(persona);
                PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
                return personaOutputDto;
            }
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public PersonaOutputDto findByIdPerson(int id) throws Exception{
        try {
            Persona persona = repositorioPersona.findById(id).orElseThrow(() -> new Exception("Error"));
            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
            return personaOutputDto;
        }catch (NotFExceptions e){
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public ResponseEntity findByIdPersonOutputType(int id, String outputType) {
        try {
            Persona persona = repositorioPersona.findById(id).orElseThrow(() -> new Exception("Error"));
            if(outputType == "simple"){
                PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
                return ResponseEntity.ok(personaOutputDto);
            }else if(outputType == "full"){
                Persona personaStudent = repositorioStudent.findByPersona(persona);
                Persona personaProfesor = repositorioProfesor.findByPersona(persona);
                if(personaStudent != null){
                    return forStudent(personaStudent, persona);
                }else if(personaProfesor != null){
                    return forProfesor(personaProfesor,persona);
                }
                return ResponseEntity.badRequest().body("Error");
            }
                return ResponseEntity.badRequest().body("Not found person");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ResponseEntity forStudent(Persona personaStudent, Persona persona){
        List<StudentEntity> studentList = repositorioStudent.findAll();
        for(StudentEntity student : studentList){
            if(student.getPersona().equals(personaStudent)){
                PersonaStudentOutputDto personaStudentOutputDto = new PersonaStudentOutputDto(persona,student);
                return ResponseEntity.ok(personaStudentOutputDto);
            }
        }
        return ResponseEntity.badRequest().body("La persona no coincide");
    }
    public ResponseEntity forProfesor(Persona personaProfesor,Persona persona){
        List<ProfesorEntity> profesorList = repositorioProfesor.findAll();
        for(ProfesorEntity profesor : profesorList){
            if(profesor.getPersona().equals(personaProfesor)){
                PersonaProfesorOutputDto personaProfesorOutputDto = new PersonaProfesorOutputDto(persona,profesor);
                return ResponseEntity.ok(personaProfesorOutputDto);
            }
        }
        return ResponseEntity.badRequest().body("La persona no coincide");
    }
    @Override
    public List<PersonaOutputDto> findNamePerson(String name) throws Exception{
        try{
            List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
            List<Persona> personaList =  repositorioPersona.findByUsuario(name);
            for(Persona persona : personaList){
                personaOutputDtoList.add(new PersonaOutputDto(persona));
            }
            return personaOutputDtoList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public ResponseEntity findNamePersonoutputType(String name,String outputType) {
        if (outputType == "simple") {
            try {
                List<PersonaOutputDto> personaOutputDtoList = new ArrayList<>();
                List<Persona> personaList = repositorioPersona.findByUsuario(name);
                for (Persona persona : personaList) {
                    personaOutputDtoList.add(new PersonaOutputDto(persona));
                }
                return ResponseEntity.ok(personaOutputDtoList);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error");
            }
        } else if (outputType == "full") {
            return fullOutPut(name);
        }
        return ResponseEntity.badRequest().body("Not found person");
    }
    public ResponseEntity fullOutPut(String name){
        List<Persona> personaList = repositorioPersona.findByUsuario(name);
        List<PersonaProfesorOutputDto> pProfesorOutputDtoList = new ArrayList<>();
        List<PersonaStudentOutputDto> pStudentOutputDtoList = new ArrayList<>();
        for (Persona persona : personaList) {
            Persona personaStudent = repositorioStudent.findByPersona(persona);
            Persona personaProfesor = repositorioProfesor.findByPersona(persona);
            if (personaStudent != null) {
                List<StudentEntity> studentList = repositorioStudent.findAll();
                for (StudentEntity student : studentList) {
                    if (student.getPersona().equals(personaStudent)) {
                        pStudentOutputDtoList.add(new PersonaStudentOutputDto(persona, student));
                    }
                }
            } else if (personaProfesor != null) {
                List<ProfesorEntity> profesorList = repositorioProfesor.findAll();
                for (ProfesorEntity profesor : profesorList) {
                    if (profesor.getPersona().equals(personaStudent)) {
                        pProfesorOutputDtoList.add(new PersonaProfesorOutputDto(persona, profesor));
                    }
                }
            }
        }
        return ResponseEntity.badRequest().body("Error");
    }
    @Override
    public List<PersonaOutputDto> findAll(){
        List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
        for (Persona persona : repositorioPersona.findAll()){
            personaOutputDtoList.add(new PersonaOutputDto(persona));
        }
        return personaOutputDtoList;
    }
    public List<PersonaOutputDto> findAlloutputType(){
        List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
        for (Persona persona : repositorioPersona.findAll()){
            personaOutputDtoList.add(new PersonaOutputDto(persona));
        }
        return personaOutputDtoList;
    }

}
