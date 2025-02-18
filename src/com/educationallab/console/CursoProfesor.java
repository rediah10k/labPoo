/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.educationallab.console;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
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
    
    @Override
    public String toString(){
        return "CursoProfesor(profesor=" + this.profesor.toString() + ",año=" + this.anio + "," + this.curso.toString() + ")";
    }
}
