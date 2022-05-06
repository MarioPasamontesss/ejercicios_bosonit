package com.ejercicios.BS41.aplication.services;

import com.ejercicios.BS41.infraestructure.controler.Variables;
import org.springframework.stereotype.Service;

@Service
public class ServiceP {
    Variables variables;
    public Variables getVariables(){
        return variables;
    }
}
