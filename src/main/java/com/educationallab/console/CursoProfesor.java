package com.educationallab.console;

import java.io.Serializable;

public class CursoProfesor implements Serializable {

    private  static  final  long serialVersionUID = 1L;
    private Profesor profesor;
    private Integer anio;
    private Integer semestre;
    private Curso curso;
 
    public CursoProfesor( Profesor profesor, Integer anio, Integer semestre, Curso curso){
        this.profesor = profesor;
        this.anio= anio;
        this.semestre = semestre;
        this.curso=curso;
    }

    public Profesor getProfesor(){
        return profesor;
    }

    public Curso getCurso(){
        return curso;
    }
    
    @Override
    public String toString(){
        return "CursoProfesor(profesor=" + this.profesor.toString() + ",año=" + this.anio +"," + this.semestre + "," + this.curso.toString() + ")";
    }
}
