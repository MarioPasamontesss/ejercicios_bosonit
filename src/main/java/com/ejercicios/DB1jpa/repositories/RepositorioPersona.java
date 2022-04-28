package com.ejercicios.DB1jpa.repositories;

import com.ejercicios.DB1jpa.persona.Persona;
import com.ejercicios.DB1jpa.persona.dto.PersonaOutputDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPersona extends JpaRepository<Persona, Integer> {
    List<Persona> findByName(String name);
}
