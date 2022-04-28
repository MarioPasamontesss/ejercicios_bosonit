package com.ejercicios.DB1jpa.services;

import com.ejercicios.DB1jpa.persona.Persona;
import com.ejercicios.DB1jpa.persona.dto.PersonaInputDto;
import com.ejercicios.DB1jpa.persona.dto.PersonaOutputDto;
import com.ejercicios.DB1jpa.repositories.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicePerson {

    @Autowired
    RepositorioPersona repositorioPersona;
    public PersonaOutputDto addPersona(PersonaInputDto personaID) throws Exception{
        if(personaID.getUsuario().length() < 6 || personaID.getUsuario().length()>10){
            throw new Exception("El usuario debe contener entre 6 y 10 caracteres");
        }else{
            Persona person = new Persona(personaID);
            repositorioPersona.save(person);
            PersonaOutputDto personaOD = new PersonaOutputDto(person);
            return personaOD;
        }
    }
    public void deletePerson(int id){ repositorioPersona.deleteById(id);}

    public PersonaOutputDto updatePerson(PersonaInputDto personaInputDto){
        Persona persona = new Persona(personaInputDto);
        repositorioPersona.save(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return  personaOutputDto;
    }

    public PersonaOutputDto findByIdPerson(int id) throws Exception{
        try {
            Persona persona = repositorioPersona.findById(id).orElseThrow(() -> new Exception("Error"));
            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
            return personaOutputDto;
        }catch (Exception e){
            return null;
        }
    }
    public List<PersonaOutputDto> findNamePerson(String name) throws Exception{
        try{
            List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
            List<Persona> personaList =  repositorioPersona.findByName(name);
            for(Persona persona : personaList){
                personaOutputDtoList.add(new PersonaOutputDto(persona));
            }
            return personaOutputDtoList;
        }catch (Exception e){
            return null;
        }
    }
    public List<PersonaOutputDto> showAll(){
        List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
        for (Persona persona : repositorioPersona.findAll()){
            personaOutputDtoList.add(new PersonaOutputDto(persona));
        }
        return personaOutputDtoList;
    }
}
