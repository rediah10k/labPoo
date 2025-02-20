package com.educationallab.console;


class Curso {
    private Integer ID;
    private Programa programa;
    private String nombre;
    private boolean activo;

    public Curso(Integer ID, Programa programa, String nombre, boolean activo) {
        this.ID = ID;
        this.programa = programa;
        this.nombre = nombre;
        this.activo = activo;
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
       return "Nombre: "+nombre+"Programa: "+programa.toString();
    }
}