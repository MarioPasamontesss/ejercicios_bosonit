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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
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
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws NotFExceptions{
        if(persona.usuario().length() < 6 || persona.usuario().length()>10){
            throw new NotFExceptions("El usuario debe contener entre 6 y 10 caracteres");
        }else{
            Persona person = new Persona(persona);
            repositorioPersona.save(person);
            PersonaOutputDto personaOD = PersonaConstructor(person);
            return personaOD;
        }
    }
    public PersonaOutputDto PersonaConstructor(Persona p){
         PersonaOutputDto personaOutputDto = new PersonaOutputDto(
                 p.getId_persona(),
                 p.getUsuario(),
                 p.getPassword(),
                 p.getName(),
                 p.getSurname(),
                 p.getCompany_email(),
                 p.getPersonal_email(),
                 p.getCity(),
                 p.isActive(),
                 p.getCreate_date(),
                 p.getImagen_url(),
                 p.getTermination_date());
         return personaOutputDto;
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
    public PersonaOutputDto updatePerson(PersonaInputDto personaInputDto, int id){
        boolean existe = repositorioPersona.existsById(id);
        if(existe){
            if(personaInputDto.usuario().length() < 6 || personaInputDto.usuario().length()>10) {
                throw new NotFExceptions("El usuario debe contener entre 6 y 10 caracteres");
            }else {
                repositorioPersona.deleteById(id);
                Persona persona = new Persona(personaInputDto);
                repositorioPersona.save(persona);
                PersonaOutputDto personaOutputDto = PersonaConstructor(persona);
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
            PersonaOutputDto personaOutputDto = PersonaConstructor(persona);
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
                PersonaOutputDto personaOutputDto = PersonaConstructor(persona);
                return ResponseEntity.ok(personaOutputDto);
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
    public List<PersonaOutputDto> findNamePerson(String name) throws Exception{
        try{
            List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
            List<Persona> personaList =  repositorioPersona.findByUsuario(name);
            for(Persona persona : personaList){
                personaOutputDtoList.add(PersonaConstructor(persona));
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
                    personaOutputDtoList.add(PersonaConstructor(persona));
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
    public List<PersonaOutputDto> findAll(){
        List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
        for (Persona persona : repositorioPersona.findAll()){
            personaOutputDtoList.add(PersonaConstructor(persona));
        }
        return personaOutputDtoList;
    }
    /* public List<PersonaOutputDto> findAlloutputType(){
     List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
     for (Persona persona : repositorioPersona.findAll()){
         personaOutputDtoList.add(new PersonaOutputDto(persona));
     }
     return personaOutputDtoList;
    }
    */
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);
        List<Predicate> predicateList = new ArrayList<>();
        conditions.forEach((field, value) ->
        {
            switch (field) {
                case "user":
                    predicateList.add(cb.equal(root.get(field), (String) value));
                    break;
                case "name":
                    predicateList.add(cb.equal(root.get(field),"%"+ (String) value + "%"));
                    break;
                case "surname":
                    predicateList.add(cb.equal(root.get(field),"%" + (String) value+ "%"));
                    break;
                case "create_date":
                    String dateCondition=(String) conditions.get("dateCondition");
                        switch (dateCondition) {
                            case "greater":
                                predicateList.add(cb.greaterThan(root.<Date>get(field), (Date) value));
                                break;
                            case "less":
                                predicateList.add(cb.lessThan(root.<Date>get(field), (Date) value));
                                break;
                        }
                    break;
                default:
                    throw new NotFExceptions("Escriba bien el campo escrito");
                    }

        });
            query.select(root).where(predicateList.toArray(new Predicate[predicateList.size()]));
            return ResponseEntity.ok(entityManager.createQuery(query).getResultList());
        }*/
}

