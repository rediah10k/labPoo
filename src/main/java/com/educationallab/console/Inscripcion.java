package com.educationallab.console;


class Inscripcion {
    private Double id;
    private Curso curso;
    private Integer anio;
    private Integer semestre;
    private Estudiante estudiante;

    public Inscripcion(Double id,Curso curso, Integer anio, Estudiante estudiante, Integer semestre) {
        this.id = id;
        this.curso = curso;
        this.anio = anio;
        this.estudiante = estudiante;
        this.semestre = semestre;
    }

    public Double getId(){
        return id;
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

    

    public void setID(Double id) {
        this.id = id;
    }
}
