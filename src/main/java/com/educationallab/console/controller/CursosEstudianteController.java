package com.educationallab.console.controller;
import com.educationallab.console.dao.*;
import com.educationallab.console.model.*;
import com.educationallab.console.view.CursosEstudiantePanel;
import javax.swing.*;
import java.util.List;


public class CursosEstudianteController {
    private final InscripcionDAO inscripcionDAO;
    private final EstudianteDAO estudianteDAO;
    private final CursoDAO cursoDAO;


    public CursosEstudianteController() {

        this.inscripcionDAO = new InscripcionDAO();
        this.estudianteDAO = new EstudianteDAO();
        this.cursoDAO = new CursoDAO();
    }

    public List<Inscripcion> cargarInscripciones() {
        return inscripcionDAO.listarInscripciones();
    }

    public List<Persona> obtenerEstudiantes() {
        return estudianteDAO.listar();
    }

    public List<Curso> obtenerCursos() {
        return cursoDAO.listarCursos();
    }



    public void agregarInscripcion(Estudiante estudiante, Curso curso, int semestre, int anio) {
        Inscripcion nuevaInscripcion = new Inscripcion(1.0, curso, anio, estudiante, semestre);
        inscripcionDAO.insertar(nuevaInscripcion);

    }

    public void editarInscripcion(Double id, Estudiante estudiante, Curso curso, int semestre, int anio) {
        Inscripcion inscripcionEditada = new Inscripcion(id, curso, anio, estudiante, semestre);
        inscripcionDAO.actualizar(inscripcionEditada);

    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta inscripción?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            inscripcionDAO.eliminar(inscripcion);

        }
    }


}
