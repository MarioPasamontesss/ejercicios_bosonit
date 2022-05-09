package com.ejercicios.DB1jpa.infraestructure.controler;

import com.ejercicios.DB1jpa.aplication.services.ServiceStudentInterface;
import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import com.ejercicios.DB1jpa.infraestructure.dto.input.AsignaturaIdInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.input.StudentInputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.PersonaStudentOutputDto;
import com.ejercicios.DB1jpa.infraestructure.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BS1/controlerStudent")
public class ControladorStudent {

    @Autowired
    ServiceStudentInterface serviceStudent;

    public ControladorStudent(ServiceStudentInterface serviceStudent) {
        this.serviceStudent = serviceStudent;
    }

    @PostMapping("/studentadd")
    public StudentOutputDto addStudent(@RequestBody StudentInputDto studentInputDto){
        try {
            StudentOutputDto studentOutputDto = serviceStudent.addStudent(studentInputDto);
            return studentOutputDto;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id){
        try {
            serviceStudent.deleteStudent(id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update/{id}")
    public StudentOutputDto updateStudent(@RequestBody StudentInputDto studentInputDto,@RequestParam String id){
        try {
            return serviceStudent.updateStudent(studentInputDto, id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* @GetMapping("/findStudent/{id}")
    public StudentOutputDto findStudentsById(@PathVariable String id){
        try {
            return serviceStudent.findIdStudent(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } */
    @GetMapping(value = "/findStudent/{id}", params = "outputType")
    public ResponseEntity findStudentsById(@PathVariable String id, @RequestParam(value = "ouputType") String ouputType){
            if(ouputType == "simple") {
                StudentOutputDto studentOutputDto;
                try {
                    studentOutputDto = serviceStudent.findIdStudent(id);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("student is null");
                }
                return ResponseEntity.ok(studentOutputDto);
            }else if(ouputType == "full"){
                PersonaStudentOutputDto personaStudentOutputDto;
                try {
                    personaStudentOutputDto = serviceStudent.findIdStudentFull(id);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("student is null");
                }
                return ResponseEntity.ok(personaStudentOutputDto);
            }else{
                return null;
            }
    }
    @PutMapping("/asignarAsig/{id}")
    public void asignarStudent_a_Asig(@RequestBody List<AsignaturaIdInputDto> asignaturaEntityList, @PathVariable String id){
            serviceStudent.modificarListAsignaturas(asignaturaEntityList, id);
    }
    @PutMapping("/designarAsig/{id}")
    public void designarStudent_a_Asig(@RequestBody List<AsignaturaIdInputDto> asignaturaEntityList, @PathVariable String id){
        serviceStudent.designarListAsignaturas(asignaturaEntityList, id);
    }

    @GetMapping("/findStudentAll")
    public List<StudentOutputDto> findStudentsAll(){
        try {
            return serviceStudent.showAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
