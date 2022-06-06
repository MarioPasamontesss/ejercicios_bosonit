package com.ejercicios.BS41.infraestructure.controler;

import com.ejercicios.BS41.domain.entity.Perfil1;
import com.ejercicios.BS41.domain.entity.Perfil2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("BS41")
public class Controlador2 {

    Perfil1 perfil1 = new Perfil1();
    Perfil2 perfil2 = new Perfil2();

    /*@PostConstruct
    public void mostrar(){
        System.out.println(perfil1.perfil1 + " " +  perfil2.perfil2);
    }
    @GetMapping("/perfil1")
    public Perfil1 getString(){
        perfil1.miFuncion();
        return perfil1;
    }
    @GetMapping("/perfil2")
    public Perfil2 getString2(){
        perfil2.miFuncion();
        return perfil2;
    }*/
}
