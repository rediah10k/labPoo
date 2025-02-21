package com.educationallab.console;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Creación de una facultad
        Persona decano = new Persona(1.0, "Carlos", "Gomez", "carlos@universidad.edu");
        Facultad facultad = new Facultad(1.0, "Ingeniería", decano);
        
        // Creación de un programa
        Programa programa = new Programa(1.0, "Ingeniería de Sistemas", 10.0, new Date(), facultad);
        
        // Creación de un curso
        Curso curso = new Curso(101, programa, "Tecnologías avanzadas", true);
        
        // Creación de un estudiante
        Estudiante estudiante = new Estudiante(2.0, "Ana", "Pérez", "ana@correo.com", 160004707, programa, true, 4.3);
        
        // Creación de un profesor
        Profesor profesor = new Profesor(3.0, "Luis", "Martínez", "luis@correo.com", "Tiempo Completo");
        
        // Inscripción de un estudiante en un curso
        Inscripcion inscripcion = new Inscripcion(2.0, curso, 2025, estudiante, 1);
        
        // Asociación de un profesor a un curso
        CursoProfesor cursoProfesor = new CursoProfesor(profesor, 2024, 1, curso);
        
        // Gestión de inscripciones
        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        inscripcionesPersonas.inscribir(estudiante);
        inscripcionesPersonas.inscribir(profesor);
        
        // Gestión de cursos inscritos
        CursosInscritos cursosInscritos = new CursosInscritos();
        cursosInscritos.inscribirCurso(inscripcion);
        
        // Gestión de cursos y profesores
        CursosProfesores cursosProfesores = new CursosProfesores();
        cursosProfesores.inscribir(cursoProfesor);
        
        // Impresión de listados
        System.out.println("Listado de cursos inscritos:");
        System.out.println(cursosInscritos);
        
        System.out.println("Listado de cursos y profesores:");
        System.out.println(cursosProfesores);
        
        System.out.println("Listado de personas inscritas:");
        inscripcionesPersonas.imprimirListado();
    }
}
