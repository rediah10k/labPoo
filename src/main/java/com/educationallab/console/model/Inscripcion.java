package com.educationallab.console.model;

import lombok.*;
import java.io.Serializable;
@Getter
@Setter

public class Inscripcion implements Serializable{
    private Double Id;
    private Curso curso;
    private Integer anio;
    private Integer semestre;
    private Estudiante estudiante;

    public Inscripcion(Double Id, Curso curso, Integer anio, Estudiante estudiante, Integer semestre) {
        this.Id=Id;
        this.curso = curso;
        this.anio = anio;
        this.estudiante = estudiante;
        this.semestre = semestre;
    }

    @Override
    public String toString(){
         return ("Inscripcion:("+  "Curso: " + curso.toString() +" Año: "+anio+" Semestre: " + semestre+ ") || " + estudiante.toString() );
    }

}
