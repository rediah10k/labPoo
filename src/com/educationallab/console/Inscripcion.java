package com.educationallab.console;


class Inscripcion {
    private Curso curso;
    private int año;
    private int semestre;
    private Estudiante estudiante;

    public Inscripcion(Curso curso, int año, Estudiante estudiante, int semestre) {
        this.curso = curso;
        this.año = año;
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
        return año;
    }

    public void setAño(int año) {
        this.año = año;
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
}
