package com.educationallab.console.controller;


import com.educationallab.console.dao.CursoDAO;
import com.educationallab.console.dao.ProgramaDAO;
import com.educationallab.console.model.Curso;
import com.educationallab.console.model.Programa;

import java.util.List;

public class CursoController {
    private final CursoDAO cursoDAO;
    private final ProgramaDAO programaDAO;

    public CursoController() {
        this.cursoDAO = new CursoDAO();
        this.programaDAO = new ProgramaDAO();
    }

    public Curso buscarPorId(int id) {
        return cursoDAO.obtenerCursoPorId(id);
    }


    public List<Programa> obtenerProgramas() {
        return programaDAO.listarProgramas();
    }

    public boolean guardar(Curso curso) {
        if(curso.getId() !=null) {
            return cursoDAO.actualizar(curso);
        } else {
            return cursoDAO.insertar(curso);
        }
    }


    public boolean eliminar(int id) {
        return cursoDAO.eliminar(id);
    }


    public List<Curso> listar() {
        return cursoDAO.listarCursos();
    }
}