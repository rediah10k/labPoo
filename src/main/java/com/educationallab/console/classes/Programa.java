package com.educationallab.console.classes;

import java.io.Serializable;
import java.util.Date;



public class Programa implements Serializable {
    private static final long serialVersionUID = 1L;
    private double id;
    private String nombre;
    private double duracion;
    private Date registro;
    private Facultad facultad;

    public Programa(double id, String nombre, double duracion, Date registro, Facultad facultad) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public double getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @Override
    public String toString() {
        return nombre+" Facultad: "+facultad.getNombre();
    }
}