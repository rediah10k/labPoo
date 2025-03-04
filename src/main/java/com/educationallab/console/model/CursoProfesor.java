<<<<<<<< HEAD:src/main/java/com/educationallab/console/model/CursoProfesor.java
package com.educationallab.console.model;
========
package com.educationallab.console.classes;
>>>>>>>> acba17daaf2aeb5effc7ce59913f0804d0635bdc:src/main/java/com/educationallab/console/classes/CursoProfesor.java

import java.io.Serializable;
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
