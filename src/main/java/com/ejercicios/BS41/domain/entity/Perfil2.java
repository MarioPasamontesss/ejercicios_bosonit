package com.ejercicios.BS41.domain.entity;

import com.ejercicios.BS41.aplication.services.Perfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("perfil2")
public class Perfil2 implements Perfiles {
    public String perfil2 = "perfil2";


    @Override
    public void miFuncion() {
        System.out.println(perfil2);
    }
}
