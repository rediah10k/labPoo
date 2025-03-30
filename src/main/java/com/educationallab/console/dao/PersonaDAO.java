package com.educationallab.console.dao;
import com.educationallab.console.model.*;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.educationallab.console.dao.EstudianteDAO;
import com.educationallab.console.dao.ProfesorDAO;

public abstract class PersonaDAO {

ProfesorDAO profesorDAO;
EstudianteDAO estudianteDAO;

    public abstract List<Persona> listar();


    public abstract Persona buscarPorId(Double id);

    public abstract void insertar(Persona persona);

    public abstract void actualizar(Persona persona);


    public abstract void eliminar(Persona persona);


}
