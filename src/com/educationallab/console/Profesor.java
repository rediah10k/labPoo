
package com.educationallab.console

public class Profesor extends Persona {
    private  static  final  long serialVersionUID = 1L;
    private String tipoContrato;
   
  
    public Empleado(Integer id, String nombres, String apellidos, String email, String tipoContrato){
        super(id, nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
        
    }

    @Override
    public String toString() {
        return "Empleado("+ super.toString() + ", tipoContrato =" + tipoContrato)";
    }
    
    
}
