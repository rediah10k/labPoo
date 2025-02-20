package com.educationallab.console;


class Facultad {
    private double ID;
    private String nombre;
    private Persona decano;

    public Facultad(String nombre, Persona decano) {
        this.nombre = nombre;
        this.decano = decano;
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