package com.educationallab.console;


import java.io.Serializable;

class Facultad implements Serializable {
    private static final long serialVersionUID = 1L;
    private double id;
    private String nombre;
    private Persona decano;

    public Facultad(double id, String nombre, Persona decano) {
        this.id= id;
        this.nombre = nombre;
        this.decano = decano;
    }

    public double getId() {
        return id;
    }
    public Persona getDecano() {
        return decano;
    }

    public void setDecano(Persona decano) {
        this.decano = decano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
       return nombre+" Decano "+decano.toString();
    }
}