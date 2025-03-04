package com.educationallab.console.dao;
import com.educationallab.console.model.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesPersonasDAO {
    @Getter
    private List<Persona> personas = new ArrayList<>();

    Profesor decano = new Profesor(3.0,"Elvis","Perez","deanatura@gmail.com","Tiempo completo");
    Facultad fcbi = new Facultad(1.0,"FCBI",decano);
    Programa sistemas = new Programa(1.0,"Sistemas",10.0, LocalDate.now(),fcbi);

    public InscripcionesPersonasDAO() {
        personas.add(new Estudiante(1.0, "Juan", "Pérez", "estudiante@gmail.com",160004523.0,sistemas,Boolean.TRUE,3.81));
        personas.add(new Profesor(2.0, "Ana", "Gómez", "profe@gmail.com","tiempo completo"));
    }

}


