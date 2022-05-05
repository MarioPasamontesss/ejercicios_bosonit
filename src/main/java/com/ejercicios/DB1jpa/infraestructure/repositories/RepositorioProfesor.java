package com.ejercicios.DB1jpa.infraestructure.repositories;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProfesor extends JpaRepository<ProfesorEntity, String> {

    Persona findByPersona(Persona p);
}
