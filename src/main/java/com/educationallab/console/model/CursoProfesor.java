
package com.educationallab.console.model;


import java.io.Serializable;

import lombok.*;

@Getter
@Setter
public class CursoProfesor implements Serializable {

    private  static  final  long serialVersionUID = 1L;
    private Double Id;
    private Profesor profesor;
    private Integer anio;
    private Integer semestre;
    private Curso curso;
 
    public CursoProfesor(Double Id, Curso curso, Integer anio, Profesor profesor,  Integer semestre){
        this.Id=Id;
        this.profesor = profesor;
        this.anio= anio;
        this.semestre = semestre;
        this.curso=curso;
    }

    
    @Override
    public String toString(){
        return this.profesor.toString() + " || Curso ("+this.curso.toString() +", "+ this.anio +" ," + this.semestre + ")";
    }
}
