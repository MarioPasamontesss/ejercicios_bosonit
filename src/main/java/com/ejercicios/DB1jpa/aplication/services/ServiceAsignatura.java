package com.ejercicios.DB1jpa.aplication.services;

import com.ejercicios.DB1jpa.common.exceptions.NotFExceptions;
import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturasInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.AsignaturasOutputDto;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioAsignatura;
import com.ejercicios.DB1jpa.infraestructure.repositories.RepositorioStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAsignatura implements ServiceAsignaturaInterface{

    @Autowired
    RepositorioAsignatura repositorioAsignatura;
    @Autowired
    RepositorioStudent repositorioStudent;

    @Override
    public AsignaturasOutputDto addAsignatura(AsignaturasInputDto asignaturasInputDto){
        AsignaturaEntity asignatura = new AsignaturaEntity(asignaturasInputDto);
        repositorioAsignatura.save(asignatura);
        AsignaturasOutputDto asignaturasOutputDto = new AsignaturasOutputDto(asignatura);
        return asignaturasOutputDto;
    }
    @Override
    public AsignaturasOutputDto updateAsignatura(AsignaturasInputDto asignaturasInputDto, String id) throws NotFExceptions {
        boolean existe = repositorioAsignatura.existsById(id);
        if(existe){
            repositorioAsignatura.deleteById(id);
            AsignaturaEntity asignatura = new AsignaturaEntity(asignaturasInputDto);
            repositorioAsignatura.save(asignatura);
            AsignaturasOutputDto asignaturasOutputDto = new AsignaturasOutputDto(asignatura);
            return asignaturasOutputDto;
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public void deleteAsignatura(String id) throws NotFExceptions {
        boolean existe = repositorioAsignatura.existsById(id);
        if(existe){
            for(StudentEntity studentEntity : repositorioStudent.findAll()){
                if(studentEntity.getId_student().equals(id)){
                    throw new NotFExceptions("Error al borrar, hay estudiantes cursando");
                }
            }
            repositorioAsignatura.deleteById(id);
        }else{
            throw new NotFExceptions("No se han encontrado registros con ese id");
        }
    }
    @Override
    public List<AsignaturasOutputDto> findAll(){
        List<AsignaturasOutputDto> asignaturasOutputDtoList = new ArrayList<>();
        for(AsignaturaEntity asignatura: repositorioAsignatura.findAll()){
            asignaturasOutputDtoList.add(new AsignaturasOutputDto(asignatura));
        }
        return asignaturasOutputDtoList;
    }
    @Override
    public AsignaturasOutputDto findIdAsignatura(String id){
        try {
            AsignaturaEntity asignatura = repositorioAsignatura.findById(id).orElseThrow(() -> new Exception("Error"));
            AsignaturasOutputDto asignaturasOutputDto = new AsignaturasOutputDto(asignatura);
            return asignaturasOutputDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<AsignaturaEntity> finStudentListAsignaturas(String id){
        List<AsignaturaEntity> asignaturaEntityList = new ArrayList<>();
        StudentEntity student = repositorioStudent.findById(id).get();
        for(AsignaturaEntity asignatura : student.getAsignaturas()){
            asignaturaEntityList.add(asignatura);
        }
        return asignaturaEntityList;
    }


}
