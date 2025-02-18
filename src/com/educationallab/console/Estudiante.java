

package com.educationallab.console;



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
    public Estudiante(Integer id, String nombres, String apellidos, String email, String codigo, String programa, Boolean activo, Double promedio){
        super(id, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
       return "Estudiante{" + super.toString() + "codigo=" + codigo + ", programa=" + programa + ", activo="+activo+", promedio="+promedio+ '}';
    }

}
