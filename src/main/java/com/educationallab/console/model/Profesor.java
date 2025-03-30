package com.educationallab.console.model;



import lombok.*;

@Getter
@Setter
public class Profesor extends Persona {
    private static final long serialVersionUID = 1L;
    private String tipoContrato;


    public Profesor(){

    }
    public Profesor(Double id, String nombres, String apellidos, String email, String tipoContrato) {
        super(id, nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return "Profesor: (" +super.toString() + ", Tipo de contrato: " + tipoContrato+")";
    }
}
