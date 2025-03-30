package com.educationallab.console.controller;

import com.educationallab.console.dao.*;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;
import com.educationallab.console.model.factories.PersonaFactory;

import java.util.List;

public class ProfesoresController {

    private final ProfesorDAO profesorDAO;

    public ProfesoresController() {
        this.profesorDAO = new ProfesorDAO();
        profesorDAO.insertarDatosSemilla();
    }

    public List<Persona> obtenerProfesores() {
        return profesorDAO.listar();
    }

    public void eliminarProfesor(Persona persona) {
        profesorDAO.eliminar(persona);
    }

    public void agregarProfesor( String nombres, String apellidos, String email,
                               String tipoContrato) {

            Profesor profesor = PersonaFactory.crearProfesor(nombres, apellidos, email, tipoContrato);
            profesorDAO.insertar(profesor);
        }




    public void editarProfesor(Double id, String nombres, String apellidos, String email, String tipoContrato) {

        Profesor persona = profesorDAO.buscarPorId(id);

        if (persona != null) {
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setEmail(email);
            persona.setTipoContrato(tipoContrato);

            }

            profesorDAO.actualizar(persona);
        }
}




