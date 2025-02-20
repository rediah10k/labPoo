package com.educationallab.console;


class Inscripcion {
    private Double ID;
    private Curso curso;
    private Integer anio;
    private Integer semestre;
    private Estudiante estudiante;

    public Inscripcion(Double ID,Curso curso, Integer anio, Estudiante estudiante, Integer semestre) {
        this.ID = ID;
        this.curso = curso;
        this.anio = anio;
        this.estudiante = estudiante;
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAño() {
        return anio;
    }

    public void setAño(Integer anio) {
        this.anio = anio;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    @Override
    public String toString(){
         return ("Curso: " + curso.toString() + "Estudiante: " + estudiante.toString() + "Semestre: " + semestre);
    }

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }
}
