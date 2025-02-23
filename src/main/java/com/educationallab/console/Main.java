package com.educationallab.console;

import com.educationallab.console.classes.*;

import java.util.Date;

public class Main {


    public static void main(String[] args) {
        //generarDatos(); ya generados en archivos .dat
        imprimirInformacion();
        actualizarInformacion();
        imprimirInformacion();
    }

    public static void generarDatos(){

        Persona decano = new Persona(1.0, "Diana", "Franco", "carlos@universidad.edu");
        Facultad facultad = new Facultad(1.0, "FCBI", decano);


        Programa sistemas = new Programa(1.0, "Ingeniería en Sistem", 10.0, new Date(), facultad);
        Programa electro = new Programa(1.0, "Ingeniería Electronica", 10.0, new Date(), facultad);


        Curso estocelectro = new Curso(101, electro, "Procesos Estocasticos", true);
        Curso estocsist = new Curso(101, sistemas, "Procesos Estocasticos", true);

        Estudiante est1 = new Estudiante(160004505.0, "Jhorman", "Carrillo", "jhorman@correo.com", 160004505, sistemas, true, 2.3);
        Estudiante est2 = new Estudiante(160004624.0, "Maria", "Arenas", "ana@correo.com", 160004624, sistemas, true, 3.5);


        Profesor prof1 = new Profesor(123456789.0, "Fabian", "Velasquez", "fvz@correo.com", "Tiempo Completo");

        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        inscripcionesPersonas.inscribir(est1);
        inscripcionesPersonas.inscribir(est2);
        inscripcionesPersonas.inscribir(prof1);

        Inscripcion inscripcion1 = new Inscripcion(2.0, estocsist, 2025, est1, 1);
        Inscripcion inscripcion2 = new Inscripcion(3.0, estocelectro, 2025, est2, 1);

        CursoProfesor asignadoEst20241 = new CursoProfesor(prof1, 2024, 1, estocsist);
        CursoProfesor asignadoEstE20241 = new CursoProfesor(prof1, 2024, 1, estocelectro);


        CursosInscritos cursosInscritos = new CursosInscritos();
        cursosInscritos.inscribirCurso(inscripcion1);
        cursosInscritos.inscribirCurso(inscripcion2);

        CursosProfesores cursosProfesores = new CursosProfesores();
        cursosProfesores.inscribir(asignadoEst20241);
        cursosProfesores.inscribir(asignadoEstE20241);

    }

    public static void imprimirInformacion(){
        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosInscritos cursosInscritos = new CursosInscritos();
        CursosProfesores cursosProfesores = new CursosProfesores();

        inscripcionesPersonas.cargarDatos();
        cursosInscritos.cargarDatos();
        cursosProfesores.cargarDatos();

        System.out.println("Inscripciones a cursos");
        System.out.println(cursosInscritos);

        System.out.println("Cursos y sus docentes:");
        System.out.println(cursosProfesores);

        System.out.println("Listado de personas registradas:");
        System.out.println(inscripcionesPersonas);
        System.out.println("\n\n");
    }

    public static void actualizarInformacion(){
        Persona decano = new Persona(1.0, "Diana", "Franco", "carlos@universidad.edu");
        Facultad facultad = new Facultad(1.0, "FCBI", decano);


        Programa sistemas = new Programa(1.0, "Ingeniería de Sistemas", 10.0, new Date(), facultad);
        Programa electro = new Programa(2.0, "Ingeniería Electronica", 10.0, new Date(), facultad);
        Estudiante est1 = new Estudiante(160004505.0, "Alexander", "Carrillo", "alex@correo.com", 160004505, sistemas, true, 2.5);
        Estudiante est2 = new Estudiante(160004624.0, "Maria", "Arenas", "ana@correo.com", 160004624, sistemas, true, 3.5);
        Curso estocelectro = new Curso(101, electro, "Procesos Estocasticos", true);
        Inscripcion inscripcion2 = new Inscripcion(3.0, estocelectro, 2025, est2, 1);

        InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas();
        CursosInscritos cursosInscritos = new CursosInscritos();
        CursosProfesores cursosProfesores = new CursosProfesores();

        inscripcionesPersonas.cargarDatos();
        cursosInscritos.cargarDatos();
        cursosProfesores.cargarDatos();

        inscripcionesPersonas.actualizar(est1);
        cursosInscritos.eliminar(inscripcion2);
        inscripcionesPersonas.eliminar(est2);


    }
}
