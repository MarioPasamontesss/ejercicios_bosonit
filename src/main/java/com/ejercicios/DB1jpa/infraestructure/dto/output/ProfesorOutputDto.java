package com.ejercicios.DB1jpa.infraestructure.dto.output;

import com.ejercicios.DB1jpa.domain.entity.Persona;
import com.ejercicios.DB1jpa.domain.entity.ProfesorEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesorOutputDto {
    String id_profesor;
    Persona persona;
    String coments;
    String branch;

    public ProfesorOutputDto (ProfesorEntity profesorEntity){
        setId_profesor(profesorEntity.getId_profesor());
        setPersona(profesorEntity.getPersona());
        setComents(profesorEntity.getComents());
        setBranch(profesorEntity.getBranch());
    }
}
