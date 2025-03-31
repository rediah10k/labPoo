package com.educationallab.console;

import com.educationallab.console.view.CursoView;
import com.educationallab.console.view.DocentesView;
import com.educationallab.console.view.EstudianteDetalleView;
import com.educationallab.console.view.EstudianteView;
import javax.swing.*;
import java.awt.*;


public class MainApplication {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema Académico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout(10, 10));


        JPanel panelFormularios = new JPanel(new GridLayout(3, 1, 10, 10));
        panelFormularios.setPreferredSize(new Dimension(350, 0));

        panelFormularios.add(new DocentesView());
        panelFormularios.add(new EstudianteView());
        panelFormularios.add(new CursoView());


        EstudianteDetalleView detalleView = new EstudianteDetalleView();

        frame.add(panelFormularios, BorderLayout.WEST);
        frame.add(detalleView, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}







