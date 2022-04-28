package com.ejercicios.DB1jpa.aplication.services;


import com.ejercicios.DB1jpa.infraestructure.controler.dto.input.PersonaInputDto;
import com.ejercicios.DB1jpa.infraestructure.controler.dto.output.PersonaOutputDto;

import java.util.List;

public interface ServicePersonInterface{
    public PersonaOutputDto addPersona(PersonaInputDto personaID) throws Exception;
    void deletePerson(int i);
    PersonaOutputDto updatePerson(PersonaInputDto personaInputDto, int id);
    PersonaOutputDto findByIdPerson(int id) throws Exception;
    List<PersonaOutputDto> findNamePerson(String name) throws Exception;
    List<PersonaOutputDto> showAll();

}
