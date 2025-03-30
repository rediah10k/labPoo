package com.educationallab.console.model.factories;

import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;

public interface PersonaFactory {
    Persona crearPersona(String nombres, String apellidos, String email);

    public static Estudiante crearEstudiante(String nombres, String apellidos, String email, Double codigo, Double promedio, Programa programa, boolean activo) {
        return new Estudiante(1.0,nombres, apellidos, email,codigo,programa,  activo, promedio);
    }

    public static Profesor crearProfesor(String nombres, String apellidos, String email, String tipoContrato) {
        return new Profesor(1.0,nombres, apellidos,email, tipoContrato);
    }
}
