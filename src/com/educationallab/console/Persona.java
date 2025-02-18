
package com.educationallab.console;

import java.io.Serializable;

public class Persona implements Serializable {

    private  static  final  long serialVersionUID = 1L;
    private Integer id;
    private String nombres;
    private String apellidos;
    private String email;


    public Persona(Integer id, String nombres, String apellidos, String email){
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }


    public Integer getId() {
        return id;
    }



    @Override
    public String toString(){
        return "Persona(id=" + this.id + ",nombres=" + this.nombres + ",apellidos=" + this.apellidos + "," + this.email + ")";
    }


}
