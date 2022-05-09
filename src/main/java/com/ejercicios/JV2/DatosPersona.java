package com.ejercicios.JV2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatosPersona {
    public static void main(String args[]){
        List<Persona> persona = new ArrayList<Persona>();
        Persona p = new Persona();
        String nombre;
        String ciudad;
        int edad;
        File archivo = new File("C:\\Users\\mario.pasamontes\\IdeaProjects\\Ejercicios\\src\\OptionalStreams\\DatosPersonas");
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            try {
                String dato = br.readLine();
                while (dato != null){
                   String[] parts = dato.split(":");
                   nombre = parts[0];
                   ciudad = parts[1];
                   if(parts.length > 2){
                       edad = Integer.valueOf(parts[2]);
                       persona.add(new Persona(nombre, ciudad, edad));
                   }
                    dato = br.readLine();
                }
                persona.stream().filter(person -> person.getEdad() < 25).forEach(s-> System.out.println(s.toString()));
                fr.close();
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

}
