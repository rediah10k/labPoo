package com.educationallab.console.dao;
import com.educationallab.console.model.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesoresDAO {
    @Getter
    private List<CursoProfesor> listado = new ArrayList<>();

    Profesor decano = new Profesor(3.0,"Elvis","Perez","deanatura@gmail.com","Tiempo completo");
    Facultad fcbi = new Facultad(1.0,"FCBI",decano);
    Programa sistemas = new Programa(1.0,"Sistemas",10.0, LocalDate.now(),fcbi);
    Profesor ana= new Profesor(2.0, "Ana", "Gómez", "profe@gmail.com","tiempo completo");
    Curso soft1=new Curso(1,sistemas,"Software 1",true);

    public CursosProfesoresDAO() {

    listado.add(new CursoProfesor(ana,2024,1,soft1));
    }

}


