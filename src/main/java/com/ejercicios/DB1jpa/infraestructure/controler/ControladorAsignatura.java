package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.aplication.services.ServiceAsignaturaInterface;
import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturasInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.AsignaturasOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlerAsignatura")
public class ControladorAsignatura {

    @Autowired
    ServiceAsignaturaInterface serviceAsignaturaInterface;

    @PostMapping("/addAsignatura")
    public AsignaturasOutputDto addAsignatura(@RequestBody AsignaturasInputDto asignaturasInputDto){
        return serviceAsignaturaInterface.addAsignatura(asignaturasInputDto);
    }

    @PutMapping("/updateAsignatura/{id}")
    public AsignaturasOutputDto updateAsignatura(@RequestBody AsignaturasInputDto asignaturasInputDto, String id){
        return serviceAsignaturaInterface.updateAsignatura(asignaturasInputDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAsignatura(@PathVariable String id){
        serviceAsignaturaInterface.deleteAsignatura(id);
    }

    @GetMapping("findAll")
    public List<AsignaturasOutputDto> findAll(){
        return serviceAsignaturaInterface.findAll();
    }

    @GetMapping("findIdAsignatura/{id}")
    public AsignaturasOutputDto findIdAsignatura(@PathVariable String id){
        return serviceAsignaturaInterface.findIdAsignatura(id);
    }
    @GetMapping("findIdStudent/{id}")
    public List<AsignaturaEntity> listaAsignaturasStudents(@PathVariable String id){
        return serviceAsignaturaInterface.finStudentListAsignaturas(id);
    }

}
