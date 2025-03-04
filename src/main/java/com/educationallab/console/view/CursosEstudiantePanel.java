package com.educationallab.console.view;

import com.educationallab.console.dao.CursosInscritosDAO;
import com.educationallab.console.dao.CursosProfesoresDAO;
import com.educationallab.console.model.CursoProfesor;
import com.educationallab.console.model.Inscripcion;
import com.educationallab.console.model.Persona;
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
    private CursosInscritosDAO cursosInscritosDAO;

    public CursosEstudiantePanel(CursosInscritosDAO cursosInscritosDAO) {
        this.cursosInscritosDAO = cursosInscritosDAO;
        this.setLayout(new BorderLayout());

        List<Inscripcion> cursosInscritos = cursosInscritosDAO.getListado();
        tableModel = new CursosInscritosTableModel(cursosInscritos);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Inscripcion inscripcion = cursosInscritos.get(row);
                        showUserInfoDialog(inscripcion, row);
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


    private void showUserInfoDialog(Inscripcion inscripcion, int row) {
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



    private void showAddUserDialog() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Inscribir Nueva Persona");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));

        JTextField txtNombres = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtCorreo = new JTextField();

        dialog.add(new JLabel("Nombres:"));
        dialog.add(txtNombres);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtApellidos);
        dialog.add(new JLabel("Correo:"));
        dialog.add(txtCorreo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {

            if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() || txtCorreo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear la nueva persona
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombres(txtNombres.getText());
            nuevaPersona.setApellidos(txtApellidos.getText());
            nuevaPersona.setEmail(txtCorreo.getText());

            // Guardar en la base de datos
            //personaDAO.addPersona(nuevaPersona);

            // Refrescar la lista y actualizar la tabla
            //tableModel.addPersona(nuevaPersona);

            // Cerrar el diálogo
            dialog.dispose();
        });

        dialog.add(btnGuardar);
        dialog.setModal(true);
        dialog.setVisible(true);
    }




}
