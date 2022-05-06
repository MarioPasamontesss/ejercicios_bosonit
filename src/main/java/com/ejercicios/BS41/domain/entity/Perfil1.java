package com.ejercicios.BS41.domain.entity;

import com.ejercicios.BS41.aplication.services.Perfiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("perfil1")
public class Perfil1 implements Perfiles {
    public String perfil1 = "perfil1";


    @Override
    public void miFuncion() {
        System.out.println(perfil1);
    }
}
