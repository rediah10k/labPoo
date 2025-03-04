package com.educationallab.console;

import com.educationallab.console.dao.*;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;
import com.educationallab.console.view.CursosDocPanel;
import com.educationallab.console.view.UserPanel;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {


        /**
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Usuarios");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JTabbedPane tabbedPane = new JTabbedPane();
            InscripcionesPersonasDAO personasDAO = new InscripcionesPersonasDAO();
            CursosProfesoresDAO cursosProfesoresDAO=new CursosProfesoresDAO();
            tabbedPane.addTab("Manejar usuarios", new UserPanel(personasDAO));
            //tabbedPane.addTab("Cursos por docente", new CursosDocPanel(cursosProfesoresDAO));
            tabbedPane.addTab("Inscripciones estudiantiles", new JPanel());

            frame.add(tabbedPane);
            frame.setVisible(true);
        });

         **/

        PersonaDAO personaDAO = new PersonaDAO();
        personaDAO.insertarDatosSemilla();

        FacultadDAO facultadDAO = new FacultadDAO();
        facultadDAO.insertarDatosSemilla();
        ProgramaDAO programaDAO = new ProgramaDAO();
        programaDAO.insertarDatosSemilla();
        Programa programa = programaDAO.obtenerProgramaPorId(1);

    // Insertar un estudiante
        Estudiante estudiante = new Estudiante(5.0, "Carlos", "López", "carlos.lopez@correo.com", 123456, programa, true, 4.5);
        personaDAO.insertar(estudiante);

    // Insertar un profesor
        Profesor profesor = new Profesor(6.0, "Ana", "García", "ana.garcia@correo.com", "Catedrático");
        personaDAO.insertar(profesor);

        List<Persona> listado = personaDAO.listarPersonas();
    }


}
