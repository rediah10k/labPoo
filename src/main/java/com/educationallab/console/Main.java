package com.educationallab.console;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creación de una facultad
        Persona decano = new Persona(1.0, "Diana", "Franco", "carlos@universidad.edu");
        Facultad facultad = new Facultad(1.0, "FCBI", decano);

        // Creación de un programa
        Programa sistemas = new Programa(1.0, "Ingeniería de Sistemas", 10.0, new Date(), facultad);
        Programa electro = new Programa(1.0, "Ingeniería Electronica", 10.0, new Date(), facultad);

        // Creación de un curso
        Curso tecno = new Curso(101, sistemas, "Tecnologías avanzadas", true);
        Curso estocelectro = new Curso(101, electro, "Procesos Estocasticos", true);
        Curso estocsist = new Curso(101, sistemas, "Procesos Estocasticos", true);


        // Creación de un estudiante
        Estudiante est1 = new Estudiante(2.0, "Jhorman", "Carrillo", "ana@correo.com", 160004507, sistemas, true, 2.3);
        Estudiante est2 = new Estudiante(2.0, "", "Carrillo", "ana@correo.com", 160004507, sistemas, true, 2.3);

        // Creación de un profesor
        Profesor prof1 = new Profesor(3.0, "Fabian", "Velasquez", "fvz@correo.com", "Tiempo Completo");

        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        inscripcionesPersonas.inscribir(est1);
        inscripcionesPersonas.inscribir(est2);
        inscripcionesPersonas.inscribir(prof1);

        Inscripcion inscripcion1 = new Inscripcion(2.0, estocsist, 2025, est1, 1);
        Inscripcion inscripcion2 = new Inscripcion(3.0, estocsist, 2025, est2, 1);

        CursoProfesor asignadoEst20241 = new CursoProfesor(prof1, 2024, 1, estocsist);





        CursosInscritos cursosInscritos = new CursosInscritos();
        cursosInscritos.inscribirCurso(inscripcion1);
        cursosInscritos.inscribirCurso(inscripcion2);

        // Gestión de cursos y profesores
        CursosProfesores cursosProfesores = new CursosProfesores();
        cursosProfesores.inscribir(asignadoEst20241);

        // Impresión de listados
        System.out.println("Inscripciones a cursos");
        System.out.println(cursosInscritos);

        System.out.println("Listado de cursos y profesores:");
        System.out.println(cursosProfesores);



        System.out.println("Listado de personas inscritas:");
        System.out.println(inscripcionesPersonas);

    }
}
