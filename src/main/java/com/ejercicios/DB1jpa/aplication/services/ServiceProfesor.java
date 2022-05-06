package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.ProfesorInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.ProfesorOutputDto;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioProfesor;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProfesor implements ServiceProfesorInterface {

    @Autowired
    RepositorioProfesor repositorioProfesor;
    @Autowired
    RepositorioStudent repositorioStudent;

    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) throws NotFExceptions {
        ProfesorEntity profesor = new ProfesorEntity(profesorInputDto);
        for(StudentEntity studentEntity : repositorioStudent.findAll()){
            if(profesor.getPersona().getId_persona() == studentEntity.getPersona().getId_persona()){
                throw new NotFExceptions("No sepuede agregar, ya pertenece este id a un estudiante");
            }
        }
        repositorioProfesor.save(profesor);
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
        return profesorOutputDto;
    }
    @Override
    public void deleteProfesor(String id) throws NotFExceptions{
        boolean existe = repositorioProfesor.existsById(id);
        if(existe){
            repositorioProfesor.deleteById(id);
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesorInputDto ,String id) throws NotFExceptions{
        boolean existe = repositorioProfesor.existsById(id);
        if(existe){
            repositorioProfesor.deleteById(id);
            ProfesorEntity profesor = new ProfesorEntity(profesorInputDto);
            repositorioProfesor.save(profesor);
            ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
            return profesorOutputDto;
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public List<ProfesorOutputDto> findAll(){
        List<ProfesorOutputDto> profesorOutputDtoList = new ArrayList<>();
        for(ProfesorEntity profesor : repositorioProfesor.findAll()){
            profesorOutputDtoList.add(new ProfesorOutputDto(profesor));
        }
        return profesorOutputDtoList;
    }
    @Override
    public ProfesorOutputDto findIdProfesor(String id){
        try {
             ProfesorEntity profesor = repositorioProfesor.findById(id).orElseThrow(() -> new Exception("Error"));
             ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
             return profesorOutputDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
