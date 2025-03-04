<<<<<<<< HEAD:src/main/java/com/educationallab/console/model/Profesor.java
package com.educationallab.console.model;
========
package com.educationallab.console.classes;
>>>>>>>> acba17daaf2aeb5effc7ce59913f0804d0635bdc:src/main/java/com/educationallab/console/classes/Profesor.java
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
        return "Docente: (" +super.toString() + ", Tipo de contrato: " + tipoContrato+")";
    }
}
