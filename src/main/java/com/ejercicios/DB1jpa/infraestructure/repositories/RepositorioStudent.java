package com.ejercicios.DB1jpa.infraestructure.repositories;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioStudent extends JpaRepository<StudentEntity, String> {
    Persona findByPersona(int id);
}
