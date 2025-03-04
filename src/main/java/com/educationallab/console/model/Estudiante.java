package com.educationallab.console.model;

import lombok.*;

@Getter
@Setter

public class Estudiante extends Persona  {

    private Double codigo;
    private Programa programa;
    private Boolean activo;
    private Double promedio;
    public Estudiante(Double id, String nombres, String apellidos, String email, double codigo, Programa programa, Boolean activo, Double promedio){
        super(id, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public Estudiante(){
        super();
    }

    

    @Override
    public String toString() {
       return "Estudiante: ("+super.toString() + "codigo:" + codigo + ", programa:" + programa.toString() + ", activo:"+activo+", promedio:"+promedio+ ')';
    }

    public boolean isActivo() {
        return activo;
    }
}
