package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.infraestructure.controler.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.controler.dto.output.PersonaOutputDto;
import com.ejercicios.DB1jpa.infraestructure.controler.repositories.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePerson implements ServicePersonInterface{

    @Autowired
    RepositorioPersona repositorioPersona;

    @Override
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
    @Override
    public void deletePerson(int id){
        boolean existe = repositorioPersona.existsById(id);
        if(existe){
            repositorioPersona.deleteById(id);
        }else{
            System.out.println("Error, existe");
        }
        }
    @Override
    public PersonaOutputDto updatePerson(PersonaInputDto personaInputDto, int id){
        Persona persona = new Persona(personaInputDto);
        boolean existe = repositorioPersona.existsById(id);
        if(existe){
            repositorioPersona.delete(persona);
            repositorioPersona.save(persona);
            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
            return personaOutputDto;
        }else{
            return null;
        }
    }
    @Override
    public PersonaOutputDto findByIdPerson(int id) throws Exception{
        try {
            Persona persona = repositorioPersona.findById(id).orElseThrow(() -> new Exception("Error"));
            PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
            return personaOutputDto;
        }catch (Exception e){
            return null;
        }
    }
    @Override
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
    @Override
    public List<PersonaOutputDto> showAll(){
        List<PersonaOutputDto>  personaOutputDtoList = new ArrayList<>();
        for (Persona persona : repositorioPersona.findAll()){
            personaOutputDtoList.add(new PersonaOutputDto(persona));
        }
        return personaOutputDtoList;
    }

}
