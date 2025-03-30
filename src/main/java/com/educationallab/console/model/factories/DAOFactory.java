package com.educationallab.console.model.factories;


import com.educationallab.console.dao.EstudianteDAO;
import com.educationallab.console.dao.PersonaDAO;
import com.educationallab.console.dao.ProfesorDAO;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;

public class DAOFactory {
    public static PersonaDAO getDAO(Persona persona) {
        if (persona instanceof Estudiante) {
            return new EstudianteDAO();
        } else if (persona instanceof Profesor) {
            return new ProfesorDAO();
        }
        throw new IllegalArgumentException("Tipo de persona desconocido");
    }
}

