package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
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
import java.util.HashMap;
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
    public Persona addPersona(Persona persona) throws NotFExceptions{
        if(persona.getUsuario().length() < 6 || persona.getUsuario().length()>10){
            throw new NotFExceptions("El usuario debe contener entre 6 y 10 caracteres");
        }else{
            //Persona person = new Persona(persona);
            return  repositorioPersona.save(persona);
        }
    }

    @Override
    public void deletePerson(int id) throws NotFExceptions{
        boolean existe = repositorioPersona.existsById(id);
        if(existe){
            for(StudentEntity studentEntity : repositorioStudent.findAll()){
                if(studentEntity.getPersona().getId_persona() == id){
                    throw new NotFExceptions("No se puede eliminar debido a que pertenece a un estudiante");
                }
            }
            for (ProfesorEntity profesor : repositorioProfesor.findAll()){
                if(profesor.getPersona().getId_persona() == id){
                    throw new NotFExceptions("No se puede eliminar debido a que pertenece a un profesor");
                }
            }
            repositorioPersona.deleteById(id);
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
        }
    @Override
    public Persona updatePerson(Persona persona, int id){
        if(repositorioPersona.existsById(id)){
            if(persona.getUsuario().length() < 6 || persona.getUsuario().length()>10) {
                throw new NotFExceptions("El usuario debe contener entre 6 y 10 caracteres");
            }else {
                repositorioPersona.deleteById(id);
                return repositorioPersona.save(persona);
            }
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public Persona findByIdPerson(int id) throws Exception{
        try {
            return repositorioPersona.findById(id).orElseThrow(() -> new Exception("Error"));
        }catch (NotFExceptions e){
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public ResponseEntity findByIdPersonOutputType(int id, String outputType) {
        try {
            Persona persona = repositorioPersona.findById(id).orElseThrow(() -> new Exception("Error"));
            if(outputType == "simple"){
                return ResponseEntity.ok(persona);
            }else if(outputType == "full"){
                Persona personaStudent = repositorioStudent.findByPersona(persona.getId_persona());
                Persona personaProfesor = repositorioProfesor.findByPersona(persona.getId_persona());
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
            if(student.getPersona().getId_persona() == personaStudent.getId_persona()){
                PersonaStudentOutputDto personaStudentOutputDto = new PersonaStudentOutputDto(persona,student);
                return ResponseEntity.ok(personaStudentOutputDto);
            }
        }
        return ResponseEntity.badRequest().body("La persona no coincide");
    }
    public ResponseEntity forProfesor(Persona personaProfesor,Persona persona){
        List<ProfesorEntity> profesorList = repositorioProfesor.findAll();
        for(ProfesorEntity profesor : profesorList){
            if(profesor.getPersona().getId_persona() == personaProfesor.getId_persona()){
                PersonaProfesorOutputDto personaProfesorOutputDto = new PersonaProfesorOutputDto(persona,profesor);
                return ResponseEntity.ok(personaProfesorOutputDto);
            }
        }
        return ResponseEntity.badRequest().body("La persona no coincide");
    }
    @Override
    public List<Persona> findNamePerson(String name) throws Exception{
        try{
            List<Persona> personaList =  repositorioPersona.findByUsuario(name);
            return personaList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public ResponseEntity findNamePersonoutputType(String name,String outputType) {
        if (outputType == "simple") {
            try {
                List<Persona> personaList = repositorioPersona.findByUsuario(name);
                return ResponseEntity.ok(personaList);
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
            Persona personaStudent = repositorioStudent.findByPersona(persona.getId_persona());
            Persona personaProfesor = repositorioProfesor.findByPersona(persona.getId_persona());
            if (personaStudent != null) {
                List<StudentEntity> studentList = repositorioStudent.findAll();
                for (StudentEntity student : studentList) {
                    if (student.getPersona().getId_persona() == personaStudent.getId_persona()) {
                        pStudentOutputDtoList.add(new PersonaStudentOutputDto(persona, student));
                    }
                }
            } else if (personaProfesor != null) {
                List<ProfesorEntity> profesorList = repositorioProfesor.findAll();
                for (ProfesorEntity profesor : profesorList) {
                    if (profesor.getPersona().getId_persona() == personaProfesor.getId_persona()) {
                        pProfesorOutputDtoList.add(new PersonaProfesorOutputDto(persona, profesor));
                    }
                }
            }
        }
        return ResponseEntity.badRequest().body("Error");
    }
    @Override
    public List<Persona> findAll(){
        List<Persona>  personaList =repositorioPersona.findAll();
        return personaList;
    }
    @Override
    public ProfesorEntity findProfesor(String id){
        ProfesorEntity profesor;
        try {
           profesor = repositorioProfesor.findById(id).orElseThrow(() -> new Exception("A teacher with this id has not been found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return profesor;
    }

    /*@Override
    public ResponseEntity<List<Persona>> getCreate_date(HashMap<String, Object> conditions) {
        return repositorioPersona.getCreate_date(conditions);
    }*/
}

