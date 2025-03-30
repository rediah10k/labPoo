package com.educationallab.console;

import com.educationallab.console.controller.CursosEstudianteController;
import com.educationallab.console.controller.CursosProfesorController;
import com.educationallab.console.controller.EstudiantesController;
import com.educationallab.console.controller.ProfesoresController;
import com.educationallab.console.view.CursosProfesorPanel;
import com.educationallab.console.view.CursosEstudiantePanel;
import com.educationallab.console.view.PersonasPanel;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión Institucional");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JTabbedPane tabbedPane = new JTabbedPane();

            PersonasPanel vistaPersonas=new PersonasPanel();
            CursosProfesorPanel vistaProfesores=new CursosProfesorPanel();
            CursosEstudiantePanel vistaInscripciones=new CursosEstudiantePanel();



            CursosEstudianteController cursosEstudianteController = new CursosEstudianteController();
            CursosProfesorController cursosProfesorController=new CursosProfesorController();
            ProfesoresController profesoresController=new ProfesoresController();
            EstudiantesController estudiantesController=new EstudiantesController();


            vistaInscripciones.setController(cursosEstudianteController);
            vistaProfesores.setController(cursosProfesorController);
            vistaPersonas.setEstudiantesController(estudiantesController);
            vistaPersonas.setProfesoresController(profesoresController);

            tabbedPane.addTab("Manejar usuarios", vistaPersonas);
            tabbedPane.addTab("Cursos por docente", vistaProfesores);
            tabbedPane.addTab("Inscripciones por estudiante", vistaInscripciones);


            frame.add(tabbedPane);
            frame.setVisible(true);
        });

    }
}
