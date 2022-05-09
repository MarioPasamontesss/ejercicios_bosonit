package com.ejercicios.BS2.domain.entity;


public class Persona {
    String Nombre;
    String Ciudad;
    int Edad;

    public Persona(String nombre, String ciudad, int edad) {
        Nombre = nombre;
        Ciudad = ciudad;
        Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "Nombre='" + Nombre + '\'' +
                ", Ciudad='" + Ciudad + '\'' +
                ", Edad='" + Edad + '\'' +
                '}';
    }
}
