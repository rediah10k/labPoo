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
    }

    public Profesor buscarPorId(Double id) {
        return profesorDAO.buscarPorId(id);
    }


    public List<Persona> obtenerProfesores() {
        return profesorDAO.listar();
    }

    public boolean eliminarProfesor(Double id) {
       return profesorDAO.eliminar(id);
    }

    public boolean agregarProfesor( Profesor profesor) {
           return profesorDAO.insertar(profesor);
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




