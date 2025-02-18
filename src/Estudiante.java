
package com.mycompany.mavenproject3;



/**
 *
 * @author Estudiante_MCA
 */
public class Estudiante extends Persona  {

    private  static  final  long serialVersionUID = 1L;
    private String codigo;
    private String programa;
    private Boolean activo;
    private Double promedio;
    public Estudiante(Integer id, String nombres, String apellidos, Direccion direccion, String codigo, String programa, Boolean activo, Double promedio){
        super(id, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" + super.toString() + "codigo=" + codigo + ", programa=" + programa + ", activo="+activo+", promedio="+promedio+ '}';
    }
   
}
