package com.educationallab.console.controller;
import com.educationallab.console.dao.*;
import com.educationallab.console.model.*;


import java.util.List;

public class EstudiantesController {
    private final EstudianteDAO estudianteDAO;
    private final ProgramaDAO programaDAO;


    public EstudiantesController() {
        this.estudianteDAO = new EstudianteDAO();
        this.programaDAO = new ProgramaDAO();
    }

    public Estudiante buscarPorId(Double id) {
        return estudianteDAO.buscarPorId(id);
    }

    public Estudiante buscarPorCodigo(Double codigo) {
        return estudianteDAO.buscarPorCodigo(codigo);
    }


    public List<Programa> obtenerProgramas() {
        return programaDAO.listarProgramas();
    }

    public List<Persona> obtenerEstudiantes() {
        return estudianteDAO.listar();
    }
    public boolean eliminarEstudiante(Double id) {
        return estudianteDAO.eliminar(id);

    }

    public boolean agregarEstudiante(Estudiante estudiante) {
          return  estudianteDAO.insertar(estudiante);

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


    public boolean eliminarEstudiantePorCodigo(Double codigo) {
        return estudianteDAO.eliminarPorCodigo(codigo);
    }
}
