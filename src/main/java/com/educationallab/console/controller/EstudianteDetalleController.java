package com.educationallab.console.controller;

public class EstudianteDetalleController {

    private final EstudiantesController estudiantesController;
    private final ProfesoresController profesoresController;
    private final CursoController cursoController;
    private final InscripcionController inscripcionController;

    public EstudianteDetalleController() {
        estudiantesController = new EstudiantesController();
        profesoresController = new ProfesoresController();
        cursoController = new CursoController();
        inscripcionController = new InscripcionController();

    }


}
