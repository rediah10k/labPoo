package com.educationallab.console.dao;
import com.educationallab.console.model.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritosDAO {
    @Getter
    private List<Inscripcion> listado = new ArrayList<>();

    Profesor decano = new Profesor(3.0,"Elvis","Perez","deanatura@gmail.com","Tiempo completo");
    Facultad fcbi = new Facultad(1.0,"FCBI",decano);
    Programa sistemas = new Programa(1.0,"Sistemas",10.0, LocalDate.now(),fcbi);
    Estudiante jose= new Estudiante(2.0, "Jose", "Barreto", "profe@gmail.com",160004322,sistemas,true,3.9);
    Curso soft1=new Curso(1,sistemas,"Software 1",true);

    public CursosInscritosDAO() {

    listado.add(new Inscripcion(1.0,soft1,2025,jose,1));
    }

}


