/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA-Joan Martinez
 */
public class Persona implements Todos, Serializable {

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
    public String informacion(){
        return this.toString();
    }
    
   
    @Override
    public String toString(){
        return "Persona(id=" + this.id + ",nombres=" + this.nombres + ",apellidos=" + this.apellidos + "," + this.email + ")";
    }
    

}
