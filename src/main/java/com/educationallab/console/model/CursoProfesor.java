
package com.educationallab.console.model;


import java.io.Serializable;

import com.educationallab.console.model.Profesor;
import lombok.*;

@Getter
@Setter
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
        return this.profesor.toString() + " || Curso ("+this.curso.toString() +", "+ this.anio +" ," + this.semestre + ")";
    }
}
