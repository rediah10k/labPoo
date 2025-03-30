package com.educationallab.console.view;
import com.educationallab.console.model.*;
import com.educationallab.console.view.tablemodels.CursosInscritosTableModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import com.educationallab.console.controller.CursosEstudianteController;


public class CursosEstudiantePanel extends JPanel {
    private JTable table;
    private CursosInscritosTableModel tableModel;
    private CursosEstudianteController controller;


    public CursosEstudiantePanel() {
        this.setLayout(new BorderLayout());

        tableModel = new CursosInscritosTableModel(List.of());
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Inscripcion inscripcion = tableModel.getInscripciones().get(row);
                        mostrarInfoInscripcion(inscripcion);
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
        btnEliminar.addActionListener(e -> eliminarInscripcionSeleccionada());

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> mostrarDialogoEditar());

        panelBotones.add(btnInscribir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        this.add(panelBotones, BorderLayout.NORTH);

    }


    public void setController(CursosEstudianteController controller) {
        this.controller = controller;
    }

    public void actualizarTabla() {
        List<Inscripcion> inscripciones=controller.cargarInscripciones();
        tableModel.setInscripciones(inscripciones);
        tableModel.fireTableDataChanged();
    }

    private void mostrarInfoInscripcion(Inscripcion inscripcion) {
        JOptionPane.showMessageDialog(this,
                "Curso: " + inscripcion.getCurso().getNombre() + "\n" +
                        "Estudiante: " + inscripcion.getEstudiante().getNombres() + " " + inscripcion.getEstudiante().getApellidos() + "\n" +
                        "Semestre: " + inscripcion.getSemestre() + "\n" +
                        "Año: " + inscripcion.getAnio(),
                "Información de Inscripción", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarDialogoAgregar() {
        List<Persona> estudiantes = controller.obtenerEstudiantes();
        List<Curso> cursos = controller.obtenerCursos();

        JComboBox<Estudiante> comboEstudiantes = new JComboBox<>(estudiantes.toArray(new Estudiante[0]));
        JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
        JTextField txtSemestre = new JTextField();
        JTextField txtAnio = new JTextField();

        Object[] inputs = {"Estudiante:", comboEstudiantes, "Curso:", comboCursos, "Semestre:", txtSemestre, "Año:", txtAnio};
        int result = JOptionPane.showConfirmDialog(this, inputs, "Agregar Inscripción", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            controller.agregarInscripcion((Estudiante) comboEstudiantes.getSelectedItem(), (Curso) comboCursos.getSelectedItem(),
                    Integer.parseInt(txtSemestre.getText()), Integer.parseInt(txtAnio.getText()));
            actualizarTabla();
        }
    }

    private void mostrarDialogoEditar() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Inscripcion inscripcion = tableModel.getInscripciones().get(row);
            List<Persona> estudiantes = controller.obtenerEstudiantes();
            List<Curso> cursos = controller.obtenerCursos();

            JComboBox<Estudiante> comboEstudiantes = new JComboBox<>(estudiantes.toArray(new Estudiante[0]));
            JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
            JTextField txtSemestre = new JTextField(String.valueOf(inscripcion.getSemestre()));
            JTextField txtAnio = new JTextField(String.valueOf(inscripcion.getAnio()));

            comboEstudiantes.setSelectedItem(inscripcion.getEstudiante());
            comboCursos.setSelectedItem(inscripcion.getCurso());

            Object[] inputs = {"Estudiante:", comboEstudiantes, "Curso:", comboCursos, "Semestre:", txtSemestre, "Año:", txtAnio};
            int result = JOptionPane.showConfirmDialog(this, inputs, "Editar Inscripción", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                controller.editarInscripcion(inscripcion.getId(), (Estudiante) comboEstudiantes.getSelectedItem(),
                        (Curso) comboCursos.getSelectedItem(), Integer.parseInt(txtSemestre.getText()), Integer.parseInt(txtAnio.getText()));
                actualizarTabla();
            }
        }
    }

    private void eliminarInscripcionSeleccionada() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Inscripcion inscripcion = tableModel.getInscripciones().get(row);
            controller.eliminarInscripcion(inscripcion);
            actualizarTabla();
        }
    }
}

