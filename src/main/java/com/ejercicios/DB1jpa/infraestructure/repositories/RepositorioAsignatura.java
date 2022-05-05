package com.ejercicios.DB1jpa.infraestructure.repositories;

import com.ejercicios.DB1jpa.domain.entity.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioAsignatura extends JpaRepository<AsignaturaEntity, String> {
}
