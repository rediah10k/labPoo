package com.educationallab.console.controller;
import com.educationallab.console.dao.*;
import com.educationallab.console.model.*;
import com.educationallab.console.view.CursosProfesorPanel;
import javax.swing.*;
import java.util.List;

public class CursosProfesorController {
    private final CursosProfesoresDAO cursosProfesoresDAO;
    private final CursoDAO cursoDAO;
    private final ProfesorDAO profesorDAO;

    public CursosProfesorController() {
        this.cursosProfesoresDAO = new CursosProfesoresDAO();
        this.cursoDAO = new CursoDAO();
        this.profesorDAO = new ProfesorDAO();
        cargarCursosProfesores();
    }

    public List<Curso> obtenerCursos() {
        List<Curso> cursos = cursoDAO.listarCursos();
        return cursos;
    }

    public List<Persona> obtenerProfesores() {
        return profesorDAO.listar();
    }

    public List<CursoProfesor> cargarCursosProfesores() {
        return cursosProfesoresDAO.listarTodos();
    }

    public void agregarCursoProfesor(Profesor profesor,Curso curso, Integer semestre, Integer año) {
        CursoProfesor cursoProfesor= new CursoProfesor(1.0,curso,año,profesor,semestre);
        if (cursosProfesoresDAO.insertar(cursoProfesor)) {

            cargarCursosProfesores();
        }
    }

    public void modificarCursoProfesor(Double Id,Profesor profesor,Curso curso, Integer semestre, Integer año) {
        CursoProfesor cursoProfesor= new CursoProfesor(Id,curso,año,profesor,semestre);
        if (cursosProfesoresDAO.actualizar(cursoProfesor)) {

            cargarCursosProfesores();
        }
    }

    public void eliminarCursoProfesor(CursoProfesor cursoProfesor) {
        if (cursosProfesoresDAO.eliminar(cursoProfesor)) {

            cargarCursosProfesores();
        }
    }

}
