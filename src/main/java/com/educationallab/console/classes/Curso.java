package com.educationallab.console.classes;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
public class Curso implements Serializable {
    private Integer id;
    private Programa programa;
    private String nombre;
    private boolean activo;

    public Curso(Integer id, Programa programa, String nombre, boolean activo) {
        this.id = id;
        this.programa = programa;
        this.nombre = nombre;
        this.activo = activo;
    }


    @Override
    public String toString(){
       return nombre+" Programa: "+programa.toString();
    }
}