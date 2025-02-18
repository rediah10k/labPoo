/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.educationallab.console;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA-Joan Martinez
 */
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString(){
        return "Persona(id=" + this.id + ",nombres=" + this.nombres + ",apellidos=" + this.apellidos + "," + this.email + ")";
    }


}