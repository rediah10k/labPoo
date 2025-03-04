
package com.educationallab.console.model;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter

public class Persona implements Serializable {

    private  static  final  long serialVersionUID = 1L;
    private Double id;
    private String nombres;
    private String apellidos;
    private String email;

    public Persona() {

    }
    public Persona(Double id, String nombres, String apellidos, String email){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }


    @Override
    public String toString(){
        return this.nombres + " " + this.apellidos + ", " + this.email+" ";
    }


}
