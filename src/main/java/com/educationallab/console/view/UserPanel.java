package com.educationallab.console.view;

import com.educationallab.console.model.Persona;
import com.educationallab.console.dao.InscripcionesPersonasDAO;
import com.educationallab.console.view.tablemodels.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class UserPanel extends JPanel {
    private JTable table;
    private UserTableModel tableModel;
    private InscripcionesPersonasDAO personasDAO;

    public UserPanel(InscripcionesPersonasDAO personaDAO) {
        this.personasDAO = personaDAO;
        this.setLayout(new BorderLayout());

        List<Persona> personas = personaDAO.getPersonas();
        tableModel = new UserTableModel(personas);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Persona persona = personas.get(row);
                        showUserInfoDialog(persona, row);
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


    private void showUserInfoDialog(Persona persona, int row) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Información del Usuario");
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(0, 1));

        dialog.add(new JLabel("ID: " + persona.getId()));
        dialog.add(new JLabel("Nombres: " + persona.getNombres()));
        dialog.add(new JLabel("Apellidos: " + persona.getApellidos()));
        dialog.add(new JLabel("Correo: " + persona.getEmail()));

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> {
            dialog.dispose();
            showEditUserDialog(persona, row);
        });

        dialog.add(btnEditar);
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



    private void showEditUserDialog(Persona persona, int row) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Editar Usuario");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));

        JTextField txtNombres = new JTextField(persona.getNombres());
        JTextField txtApellidos = new JTextField(persona.getApellidos());
        JTextField txtCorreo = new JTextField(persona.getEmail());

        dialog.add(new JLabel("Nombres:"));
        dialog.add(txtNombres);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtApellidos);
        dialog.add(new JLabel("Correo:"));
        dialog.add(txtCorreo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {

            persona.setNombres(txtNombres.getText());
            persona.setApellidos(txtApellidos.getText());
            persona.setEmail(txtCorreo.getText());


            // Guardar en la base de datos
            //personaDAO.updatePersona(persona);

            // Refrescar la tabla
            //tableModel.fireTableRowsUpdated(row, row);

            dialog.dispose();
        });

        dialog.add(btnGuardar);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
}
