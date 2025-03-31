package com.educationallab.console.dao;
import com.educationallab.console.model.*;

import java.util.List;

public abstract class PersonaDAO {

ProfesorDAO profesorDAO;
EstudianteDAO estudianteDAO;

    public abstract List<Persona> listar();


    public abstract Persona buscarPorId(Double id);

    public abstract boolean insertar(Persona persona);

    public abstract void actualizar(Persona persona);


    public abstract boolean eliminar(Double id);


}
