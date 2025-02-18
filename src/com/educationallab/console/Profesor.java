
package com.educationallab.console;


/**
 *
 * @author Estudiante_MCA Joan Martinez y Johan Arango
 */

public class Profesor extends Persona {
    private  static  final  long serialVersionUID = 1L;
    private String tipoContrato;


    public Profesor(Integer id, String nombres, String apellidos, String email, String tipoContrato){
        super(id, nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
        
    }

    @Override
    public String toString() {
        return "Profesor {"+ super.toString() + " tipoContrato=" + tipoContrato;

    }


    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
}
