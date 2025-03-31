package com.educationallab.console.controller;

import com.educationallab.console.dao.InscripcionDAO;
import com.educationallab.console.model.Inscripcion;
import java.util.List;

public class InscripcionController {
    private final InscripcionDAO inscripcionDAO;

    public InscripcionController() {
        this.inscripcionDAO = new InscripcionDAO();
    }


    public List<Inscripcion> listarInscripcionesPorEstudiante(double estudianteId) {
        return inscripcionDAO.listarInscripcionesPorEstudiante(estudianteId);
    }


    public boolean agregarInscripcion(Inscripcion inscripcion) {
        return inscripcionDAO.insertar(inscripcion);
    }
}