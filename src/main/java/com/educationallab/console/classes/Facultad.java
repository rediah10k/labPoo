package com.educationallab.console.classes;

import lombok.*;
import java.io.Serializable;
@Getter
@Setter

public class Facultad implements Serializable{
    private double id;
    private String nombre;
    private Persona decano;

    public Facultad(double id, String nombre, Persona decano) {
        this.id= id;
        this.nombre = nombre;
        this.decano = decano;
    }


    @Override
    public String toString(){
       return nombre+" Decano "+decano.toString();
    }
}