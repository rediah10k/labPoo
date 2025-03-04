package com.educationallab.console;

import com.educationallab.console.dao.*;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;
import com.educationallab.console.view.CursosDocPanel;
import com.educationallab.console.view.CursosEstudiantePanel;
import com.educationallab.console.view.UserPanel;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        PersonaDAO personaDAO = new PersonaDAO();
        personaDAO.insertarDatosSemilla();

        FacultadDAO facultadDAO = new FacultadDAO();
        facultadDAO.insertarDatosSemilla();
        ProgramaDAO programaDAO = new ProgramaDAO();
        programaDAO.insertarDatosSemilla();
        CursoDAO cursoDAO = new CursoDAO();
        cursoDAO.insertarDatosSemilla();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión Institucional");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JTabbedPane tabbedPane = new JTabbedPane();
            InscripcionesPersonasDAO personasDAO = new InscripcionesPersonasDAO();
            InscripcionDAO inscripcionDAO = new InscripcionDAO();

            //CursosProfesoresDAO cursosProfesoresDAO=new CursosProfesoresDAO();
            tabbedPane.addTab("Manejar usuarios", new UserPanel(personaDAO));
            //tabbedPane.addTab("Cursos por docente", new CursosDocPanel(inscripcionDAO));
            tabbedPane.addTab("Inscripciones estudiantiles", new CursosEstudiantePanel(inscripcionDAO));

            frame.add(tabbedPane);
            frame.setVisible(true);
        });

    }

}
