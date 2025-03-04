package com.educationallab.console.model;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter

public class Programa implements Serializable {
    private double id;
    private String nombre;
    private double duracion;
    private String registro;
    private Facultad facultad;

    public Programa(double id, String nombre, double duracion, LocalDate registro, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = String.valueOf(registro);
        this.facultad = facultad;
    }


    @Override
    public String toString() {
        return "Nombre: "+nombre+" Facultad: "+facultad.getNombre();
    }
}