package com.ejercicios.DB1jpa.aplication.services;


import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaOutputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaStudentOutputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface ServicePersonInterface{
    Persona addPersona(Persona persona) throws Exception;
    void deletePerson(int i);
    Persona updatePerson(Persona persona, int id);
    Persona findByIdPerson(int id) throws Exception;
    ResponseEntity findByIdPersonOutputType(int id, String ouputType);
    List<Persona> findNamePerson(String name) throws Exception;
    ResponseEntity findNamePersonoutputType(String name,String outputType);
    List<Persona> findAll();
    ProfesorEntity findProfesor(String id);
    //List<PersonaOutputDto> findAlloutputType();
    //ResponseEntity<List<Persona>> getCreate_date(HashMap<String, Object> conditions);
}
