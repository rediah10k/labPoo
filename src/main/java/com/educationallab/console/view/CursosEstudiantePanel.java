package com.educationallab.console.view;

import com.educationallab.console.dao.*;
import com.educationallab.console.model.*;
import com.educationallab.console.view.tablemodels.CursosInscritosTableModel;
import com.educationallab.console.view.tablemodels.CursosProfesoresTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class CursosEstudiantePanel extends JPanel {
    private JTable table;
    private CursosInscritosTableModel tableModel;
    private InscripcionDAO inscripcionDAO;
    List<Inscripcion> inscripciones;

    public CursosEstudiantePanel(InscripcionDAO inscripcionDAO) {
        this.inscripcionDAO = inscripcionDAO;
        this.setLayout(new BorderLayout());

        inscripciones=inscripcionDAO.listarInscripciones();
        tableModel = new CursosInscritosTableModel(inscripciones);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Inscripcion inscripcion = inscripciones.get(row);
                        showInsInfoDialog(inscripcion, row);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> showAddInsDialog());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> deleteSelectedUser());

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> showEditInsDialog());

        panelBotones.add(btnInscribir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        this.add(panelBotones, BorderLayout.NORTH);
    }


    private void showInsInfoDialog(Inscripcion inscripcion, int row) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Información del curso y su docente");
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(0, 1));

        dialog.add(new JLabel("Curso: " + inscripcion.getCurso().getNombre()));
        dialog.add(new JLabel("Nombre del estudiante: " + inscripcion.getEstudiante().getNombres()+" "+inscripcion.getEstudiante().getApellidos()));

        dialog.add(new JLabel("Semestre: " + inscripcion.getSemestre()));
        dialog.add(new JLabel("Año: " + inscripcion.getAnio()));

        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private void showAddInsDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Agregar Inscripción");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));
        PersonaDAO personaDAO = new PersonaDAO();
        CursoDAO cursoDAO = new CursoDAO();
        // Obtener datos de Cursos y Estudiantes
        List<Estudiante> estudiantes = personaDAO.listarEstudiantes();
        List<Curso> cursos = cursoDAO.listarCursos();

        JComboBox<Persona> comboEstudiantes = new JComboBox<>(estudiantes.toArray(new Persona[0]));
        JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
        JTextField txtSemestre = new JTextField();
        JTextField txtAnio = new JTextField();

        dialog.add(new JLabel("Estudiante:"));
        dialog.add(comboEstudiantes);
        dialog.add(new JLabel("Curso:"));
        dialog.add(comboCursos);
        dialog.add(new JLabel("Semestre:"));
        dialog.add(txtSemestre);
        dialog.add(new JLabel("Año:"));
        dialog.add(txtAnio);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
            Curso curso = (Curso) comboCursos.getSelectedItem();
            int semestre = Integer.parseInt(txtSemestre.getText());
            int anio = Integer.parseInt(txtAnio.getText());

            Inscripcion nuevaInscripcion = new Inscripcion(curso,anio,estudiante,semestre);
            inscripcionDAO.insertar(nuevaInscripcion);
            refreshTable();
            dialog.dispose();
        });

        dialog.add(btnGuardar);
        dialog.setModal(true);
        dialog.setVisible(true);
    }


    private void showEditInsDialog() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Inscripcion inscripcion = inscripciones.get(row);

        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Inscripción");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));
        PersonaDAO personaDAO = new PersonaDAO();
        CursoDAO cursoDAO = new CursoDAO();
        // Obtener listas de estudiantes y cursos
        List<Estudiante> estudiantes = personaDAO.listarEstudiantes();
        List<Curso> cursos = cursoDAO.listarCursos();

        JComboBox<Persona> comboEstudiantes = new JComboBox<>(estudiantes.toArray(new Persona[0]));
        JComboBox<Curso> comboCursos = new JComboBox<>(cursos.toArray(new Curso[0]));
        JTextField txtSemestre = new JTextField(String.valueOf(inscripcion.getSemestre()));
        JTextField txtAnio = new JTextField(String.valueOf(inscripcion.getAnio()));

        comboEstudiantes.setSelectedItem(inscripcion.getEstudiante());
        comboCursos.setSelectedItem(inscripcion.getCurso());

        dialog.add(new JLabel("Estudiante:"));
        dialog.add(comboEstudiantes);
        dialog.add(new JLabel("Curso:"));
        dialog.add(comboCursos);
        dialog.add(new JLabel("Semestre:"));
        dialog.add(txtSemestre);
        dialog.add(new JLabel("Año:"));
        dialog.add(txtAnio);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.addActionListener(e -> {
            Estudiante estudiante = (Estudiante) comboEstudiantes.getSelectedItem();
            Curso curso = (Curso) comboCursos.getSelectedItem();
            int semestre = Integer.parseInt(txtSemestre.getText());
            int anio = Integer.parseInt(txtAnio.getText());

            Inscripcion inscripcionEditada = new Inscripcion(curso,anio,estudiante,semestre );
            inscripcionDAO.actualizar(inscripcionEditada);
            refreshTable();
            dialog.dispose();
        });

        dialog.add(btnGuardar);
        dialog.setModal(true);
        dialog.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(this, "Seleccione una inscripcion para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedUser() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Inscripcion ins = inscripciones.get(row);
        int confirm = JOptionPane.showConfirmDialog(null, "¿Desea eliminar esta inscripción?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            inscripcionDAO.eliminar(ins);
            refreshTable();
        }
        }else {
            JOptionPane.showMessageDialog(this, "Seleccione una inscripcion para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void refreshTable() {
        inscripciones = inscripcionDAO.listarInscripciones();
        tableModel.setInscripciones(inscripciones);
        tableModel.fireTableDataChanged();
    }




}
