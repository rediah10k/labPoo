package com.educationallab.console.controller;
import com.educationallab.console.dao.*;
import com.educationallab.console.model.*;

import com.educationallab.console.model.factories.PersonaFactory;


import java.util.List;

public class EstudiantesController {
    private final EstudianteDAO estudianteDAO;
    private final ProgramaDAO programaDAO;
    private final FacultadDAO facultadDAO;
    private final CursoDAO cursoDAO;

    public EstudiantesController() {
        this.estudianteDAO = new EstudianteDAO();
        this.programaDAO = new ProgramaDAO();
        this.facultadDAO = new FacultadDAO();
        this.cursoDAO = new CursoDAO();
        facultadDAO.insertarDatosSemilla();
        programaDAO.insertarDatosSemilla();
        cursoDAO.insertarDatosSemilla();
    }

    public List<Programa> obtenerProgramas() {
        return programaDAO.listarProgramas();
    }

    public List<Persona> obtenerEstudiantes() {
        return estudianteDAO.listar();
    }
    public void eliminarEstudiante(Persona persona) {
        estudianteDAO.eliminar(persona);
    }

    public void agregarEstudiante(String tipoPersona, String nombres, String apellidos, String email,
                               Programa programa, Double codigo, Double promedio, Boolean activo) {

        if ("Estudiante".equals(tipoPersona)) {
            Estudiante estudiante = PersonaFactory.crearEstudiante(nombres, apellidos, email, codigo, promedio, programa, activo);
            estudianteDAO.insertar(estudiante);
        } else {
            throw new IllegalArgumentException("Tipo de persona no válido");
        }

    }


    public void editarEstudiante(Double id, String nombres, String apellidos, String email, Double codigo, Double promedio,
                              boolean activo,Programa programa) {
        Estudiante persona = estudianteDAO.buscarPorId(id);

        if (persona != null) {
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setEmail(email);
            persona.setCodigo(codigo);
            persona.setPromedio(promedio);
            persona.setActivo(activo);
            persona.setPrograma(programa);

            estudianteDAO.actualizar(persona);
        }
    }





}
