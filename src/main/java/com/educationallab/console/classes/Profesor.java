package com.educationallab.console.classes;
import lombok.*;

@Getter
@Setter
public class Profesor extends Persona {
    private static final long serialVersionUID = 1L;
    private String tipoContrato;

    public Profesor(Double id, String nombres, String apellidos, String email, String tipoContrato) {
        super(id, nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return "Docente: (" +super.toString() + ", Tipo de contrato: " + tipoContrato+")";
    }
}
