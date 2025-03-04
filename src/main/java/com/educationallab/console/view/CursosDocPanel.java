package com.educationallab.console.view;

import com.educationallab.console.dao.CursoDAO;
import com.educationallab.console.model.*;
import com.educationallab.console.dao.CursosProfesoresDAO;
import com.educationallab.console.view.tablemodels.CursosProfesoresTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;



public class CursosDocPanel extends JPanel {

    /**
    private JTable table;
    private CursosProfesoresTableModel tableModel;
    private CursosProfesoresDAO cursosProfesoresDAO;

    public CursosDocPanel(CursosProfesoresDAO cursosProfesoresDAO) {
        this.cursosProfesoresDAO = cursosProfesoresDAO;
        this.setLayout(new BorderLayout());

        List<CursoProfesor> cursosProfesores = cursosProfesoresDAO.getListado();
        tableModel = new CursosProfesoresTableModel(cursosProfesores);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        CursoProfesor cursoProfesor = cursosProfesores.get(row);
                        showUserInfoDialog(cursoProfesor, row);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        JButton btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> {
            showAddUserDialog();
        });
        this.add(btnInscribir, BorderLayout.NORTH);
    }


    private void showUserInfoDialog(CursoProfesor cursoProfesor, int row) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Información del curso y su docente");
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(0, 1));

        dialog.add(new JLabel("Curso: " + cursoProfesor.getCurso().getNombre()));
        dialog.add(new JLabel("Nombre del docente: " + cursoProfesor.getProfesor().getNombres()+" "+cursoProfesor.getProfesor().getApellidos()));

        dialog.add(new JLabel("Semestre: " + cursoProfesor.getSemestre()));
        dialog.add(new JLabel("Año: " + cursoProfesor.getAnio()));

        dialog.setModal(true);
        dialog.setVisible(true);
    }


    private void showAddUserDialog() {
        // Crear el marco del diálogo
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Agregar Curso Profesor", true);
        dialog.setLayout(new GridLayout(4, 2));

        // Labels y componentes de entrada
        JLabel lblProfesor = new JLabel("Docente:");
        JComboBox<Profesor> comboProfesor = new JComboBox<>();

        JLabel lblCurso = new JLabel("Curso:");
        JComboBox<Curso> comboCurso = new JComboBox<>();

        JLabel lblSemestre = new JLabel("Semestre:");
        JTextField txtSemestre = new JTextField();

        JLabel lblAnio = new JLabel("Año:");
        JTextField txtAnio = new JTextField();

        // Obtener los datos desde los DAOs y añadirlos a los JComboBox
        List<Profesor> profesores = new ProfesorDAO(connection).obtenerTodos();
        profesores.forEach(comboProfesor::addItem); // Añadir cada Profesor al combo

        List<Curso> cursos = new CursoDAO(connection).obtenerTodos();
        cursos.forEach(comboCurso::addItem); // Añadir cada Curso al combo

        // Botón de acción
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            Profesor profesorSeleccionado = (Profesor) comboProfesor.getSelectedItem();
            Curso cursoSeleccionado = (Curso) comboCurso.getSelectedItem();
            int semestre = Integer.parseInt(txtSemestre.getText());
            int anio = Integer.parseInt(txtAnio.getText());

            // Crear una nueva relación CursoProfesor
            CursoProfesor cursoProfesor = new CursoProfesor(profesorSeleccionado, anio, semestre, cursoSeleccionado);

            // Guardar el nuevo curso-profesor en la base de datos
            boolean exito = new CursosProfesoresDAO(connection).agregarCursoProfesor(cursoProfesor);
            if (exito) {
                JOptionPane.showMessageDialog(dialog, "Asignación guardada correctamente.");
                tableModel.fireTableDataChanged(); // Actualizar la tabla
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Ocurrió un error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Añadir componentes al diálogo
        dialog.add(lblProfesor);
        dialog.add(comboProfesor);

        dialog.add(lblCurso);
        dialog.add(comboCurso);

        dialog.add(lblSemestre);
        dialog.add(txtSemestre);

        dialog.add(lblAnio);
        dialog.add(txtAnio);

        dialog.add(new JLabel()); // Espaciador
        dialog.add(btnGuardar);

        // Configurar el tamaño del diálogo y mostrarlo
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

**/





}
