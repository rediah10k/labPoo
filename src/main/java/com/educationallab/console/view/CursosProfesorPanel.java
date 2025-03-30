package com.educationallab.console.view;

import com.educationallab.console.controller.CursosProfesorController;
import com.educationallab.console.model.*;
import com.educationallab.console.view.tablemodels.CursosProfesoresTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CursosProfesorPanel extends JPanel {
    private JTable table;
    private CursosProfesoresTableModel tableModel;
    private CursosProfesorController controller;

    public CursosProfesorPanel() {
        this.setLayout(new BorderLayout());

        tableModel = new CursosProfesoresTableModel(List.of());
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        CursoProfesor cursoProfesor = tableModel.getCursosProfesores().get(row);
                        mostrarInfoCursoProfesor(cursoProfesor);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> mostrarDialogoAgregar());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> eliminarCursoProfesorSeleccionado());

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> mostrarDialogoEditar());

        panelBotones.add(btnInscribir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        this.add(panelBotones, BorderLayout.NORTH);

    }

    public void setController(CursosProfesorController controller) {
        this.controller = controller;
    }

    public void actualizarTabla() {
        List<CursoProfesor> cursosProfesores=controller.cargarCursosProfesores();
        tableModel.setCursosProfesores(cursosProfesores);
        tableModel.fireTableDataChanged();
    }

    private void mostrarInfoCursoProfesor(CursoProfesor cursoProfesor) {
        JOptionPane.showMessageDialog(this,
                "Curso: " + cursoProfesor.getCurso().getNombre() + "\n" +
                        "Profesor: " + cursoProfesor.getProfesor().getNombres() + " " + cursoProfesor.getProfesor().getApellidos() + "\n" +
                        "Semestre: " + cursoProfesor.getSemestre() + "\n" +
                        "Año: " + cursoProfesor.getAnio(),
                "Información de CursoProfesor", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarDialogoAgregar() {
        List<Persona> profesores = controller.obtenerProfesores();
        List<Curso> cursos = controller.obtenerCursos();

        JComboBox<Profesor> comboProfesores = new JComboBox<>(profesores.toArray(new Profesor[0]));
        JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
        JTextField txtSemestre = new JTextField();
        JTextField txtAnio = new JTextField();

        Object[] inputs = {"Profesor:", comboProfesores, "Curso:", comboCursos, "Semestre:", txtSemestre, "Año:", txtAnio};
        int result = JOptionPane.showConfirmDialog(this, inputs, "Agregar Curso Profesor", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            controller.agregarCursoProfesor((Profesor) comboProfesores.getSelectedItem(), (Curso) comboCursos.getSelectedItem(),
                    Integer.parseInt(txtSemestre.getText()), Integer.parseInt(txtAnio.getText()));
            actualizarTabla();
        }
    }

    private void mostrarDialogoEditar() {
        int row = table.getSelectedRow();
        if (row != -1) {
            CursoProfesor cursoProfesor = tableModel.getCursosProfesores().get(row);
            List<Persona> profesores = controller.obtenerProfesores();
            List<Curso> cursos = controller.obtenerCursos();

            JComboBox<Profesor> comboProfesores = new JComboBox<>(profesores.toArray(new Profesor[0]));
            JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
            JTextField txtSemestre = new JTextField(String.valueOf(cursoProfesor.getSemestre()));
            JTextField txtAnio = new JTextField(String.valueOf(cursoProfesor.getAnio()));

            comboProfesores.setSelectedItem(cursoProfesor.getProfesor());
            comboCursos.setSelectedItem(cursoProfesor.getCurso());

            Object[] inputs = {"Profesor:", comboProfesores, "Curso:", comboCursos, "Semestre:", txtSemestre, "Año:", txtAnio};
            int result = JOptionPane.showConfirmDialog(this, inputs, "Editar Curso Profesor", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                controller.modificarCursoProfesor(cursoProfesor.getId(), (Profesor) comboProfesores.getSelectedItem(),
                        (Curso) comboCursos.getSelectedItem(), Integer.parseInt(txtSemestre.getText()), Integer.parseInt(txtAnio.getText()));
            actualizarTabla();
            }
        }
    }

    private void eliminarCursoProfesorSeleccionado() {
        int row = table.getSelectedRow();
        if (row != -1) {
            CursoProfesor cursoProfesor = tableModel.getCursosProfesores().get(row);
            controller.eliminarCursoProfesor(cursoProfesor);
            actualizarTabla();
        }
    }
}
