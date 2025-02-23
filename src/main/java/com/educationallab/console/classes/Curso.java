package com.educationallab.console.classes;


import java.io.Serializable;

public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public Integer getId() {
        return id;
    }
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
       return nombre+" Programa: "+programa.toString();
    }
}