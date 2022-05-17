package com.ejercicios.DB1jpa.infraestructure.dto.output;


import com.sun.istack.NotNull;



import java.util.Date;

//@Data
//@NoArgsConstructor
public record PersonaOutputDto(@NotNull int id_persona,
                              String usuario,
                              String password,
                              String name,
                              String surname,
                              String company_email,
                              String personal_email,
                              String city,
                              boolean active,
                              Date create_date,
                              String imagen_url,
                              Date termination_date) {

    /*public PersonaOutputDto(Persona persona){
        setId_persona(persona.getId_persona());
        setUsuario(persona.getUsuario());
        setPassword(persona.getPassword());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCity(persona.getCity());
        setActive(persona.isActive());
        setCreate_date(persona.getCreate_date());
        setImagen_url(persona.getImagen_url());
        setTermination_date(persona.getTermination_date());
    }*/
}
