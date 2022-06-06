package com.ejercicios.DB1jpa.infraestructure.repositories;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;

@Repository
public interface RepositorioPersona extends JpaRepository<Persona, Integer> {
    List<Persona> findByUsuario(String user);
    //List<Persona> getCreate_date(HashMap<String, Data> conditions);
    //ResponseEntity<List<Persona>> getCreate_date(HashMap<String, Object> conditions);
}
