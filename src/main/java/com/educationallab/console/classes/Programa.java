package com.educationallab.console.classes;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter

public class Programa implements Serializable {
    private double id;
    private String nombre;
    private double duracion;
    private String registro;
    private Facultad facultad;

    public Programa(double id, String nombre, double duracion, String registro, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }


    @Override
    public String toString() {
        return nombre+" Facultad: "+facultad.getNombre();
    }
}