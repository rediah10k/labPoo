package com.educationallab.console.view;

import com.educationallab.console.dao.PersonaDAO;
import com.educationallab.console.dao.ProgramaDAO;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.dao.InscripcionesPersonasDAO;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;
import com.educationallab.console.view.tablemodels.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class UserPanel extends JPanel {
    private JTable table;
    private UserTableModel tableModel;
    private PersonaDAO personaDAO;
    List<Persona> personas;

    public UserPanel(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
        this.setLayout(new BorderLayout());
        personas=personaDAO.listarPersonas();

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

        JPanel panelBotones = new JPanel();
        JButton btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> showAddUserDialog());

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> deleteSelectedUser());

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> showEditUserDialog());

        panelBotones.add(btnInscribir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        this.add(panelBotones, BorderLayout.NORTH);
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
            showEditUserDialog();
        });

        dialog.add(btnEditar);
        dialog.setModal(true);
        dialog.setVisible(true);
    }



    private void showAddUserDialog() {
        JDialog dialog = new JDialog();
        ProgramaDAO ProgramaDAO = new ProgramaDAO();
        List<Programa> programas= ProgramaDAO.listarProgramas();
        dialog.setTitle("Inscribir Nueva Persona");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));

        JTextField txtNombres = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtCorreo = new JTextField();
        JComboBox<String> comboTipoPersona = new JComboBox<>();
        JTextField txtTipoContrato = new JTextField();
        JComboBox<Programa> comboPrograma = new JComboBox<>();
        JTextField txtCodigo = new JTextField();
        JTextField txtPromedio = new JTextField();
        JCheckBox chkActivo = new JCheckBox();



        dialog.add(new JLabel("Nombres:"));
        dialog.add(txtNombres);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtApellidos);
        dialog.add(new JLabel("Correo:"));
        dialog.add(txtCorreo);
        dialog.add(new JLabel("Tipo de usuario:"));
        comboTipoPersona.addItem("Estudiante");
        comboTipoPersona.addItem("Profesor");
        dialog.add(comboTipoPersona);
        dialog.add(new JLabel("Tipo de contrato:"));
        dialog.add(txtTipoContrato);
        dialog.add(new JLabel("Programa:"));
        for (Programa p : programas) {
            comboPrograma.addItem(p);
        }
        dialog.add(comboPrograma);

        dialog.add(new JLabel("Codigo:"));
        dialog.add(txtCodigo);
        dialog.add(new JLabel("Promedio:"));
        dialog.add(txtPromedio);
        dialog.add(new JLabel("Activo? (true/false):"));
        dialog.add(chkActivo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() ||
                    txtCorreo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (comboTipoPersona.getSelectedItem().equals("Estudiante")) {
                Estudiante nuevaPersona = new Estudiante();
                nuevaPersona.setNombres(txtNombres.getText());
                nuevaPersona.setApellidos(txtApellidos.getText());
                nuevaPersona.setEmail(txtCorreo.getText());
                nuevaPersona.setCodigo(Double.parseDouble(txtCodigo.getText()));
                nuevaPersona.setPromedio(Double.parseDouble(txtPromedio.getText()));
                nuevaPersona.setActivo(chkActivo.isSelected());
                nuevaPersona.setPrograma(comboPrograma.getItemAt(comboPrograma.getSelectedIndex()));
                personaDAO.insertar(nuevaPersona);
            } else if (comboTipoPersona.getSelectedItem().equals("Profesor")) {
                Profesor nuevaPersona = new Profesor();
                nuevaPersona.setNombres(txtNombres.getText());
                nuevaPersona.setApellidos(txtApellidos.getText());
                nuevaPersona.setEmail(txtCorreo.getText());
                nuevaPersona.setTipoContrato(txtTipoContrato.getText());
                personaDAO.insertar(nuevaPersona);
            }

            // ACTUALIZAR LISTA Y TABLA
            refreshTable();

            dialog.dispose();
        });


        dialog.add(btnGuardar);
        dialog.setModal(true);
        dialog.setVisible(true);
    }


    private void showEditUserDialog() {

        int row = table.getSelectedRow();
        if (row != -1) {
            Persona persona = personas.get(row);

        JDialog dialog = new JDialog();
        ProgramaDAO programaDAO = new ProgramaDAO();
        List<Programa> programas = programaDAO.listarProgramas();

        dialog.setTitle("Editar Usuario");
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(0, 2));

        JTextField txtNombres = new JTextField(persona.getNombres());
        JTextField txtApellidos = new JTextField(persona.getApellidos());
        JTextField txtCorreo = new JTextField(persona.getEmail());
        JComboBox<String> comboTipoPersona = new JComboBox<>(new String[]{"Estudiante", "Profesor"});
        JComboBox<Programa> comboPrograma = new JComboBox<>();
        JTextField txtTipoContrato = new JTextField();
        JTextField txtCodigo = new JTextField();
        JTextField txtPromedio = new JTextField();
        JCheckBox chkActivo = new JCheckBox();

        // Cargar programas en el combo box
        for (Programa p : programas) {
            comboPrograma.addItem(p);
        }

        // Identificar si es Estudiante o Profesor
        if (persona instanceof Estudiante) {
            comboTipoPersona.setSelectedItem("Estudiante");
            Estudiante estudiante = (Estudiante) persona;
            txtCodigo.setText(String.valueOf(estudiante.getCodigo()));
            txtPromedio.setText(String.valueOf(estudiante.getPromedio()));
            chkActivo.setSelected(estudiante.isActivo());
            comboPrograma.setSelectedItem(estudiante.getPrograma());
        } else if (persona instanceof Profesor) {
            comboTipoPersona.setSelectedItem("Profesor");
            Profesor profesor = (Profesor) persona;
            txtTipoContrato.setText(profesor.getTipoContrato());
        }

        dialog.add(new JLabel("Nombres:"));
        dialog.add(txtNombres);
        dialog.add(new JLabel("Apellidos:"));
        dialog.add(txtApellidos);
        dialog.add(new JLabel("Correo:"));
        dialog.add(txtCorreo);
        dialog.add(new JLabel("Tipo de usuario:"));
        dialog.add(comboTipoPersona);
        dialog.add(new JLabel("Tipo de contrato:"));
        dialog.add(txtTipoContrato);
        dialog.add(new JLabel("Programa:"));
        dialog.add(comboPrograma);
        dialog.add(new JLabel("Codigo:"));
        dialog.add(txtCodigo);
        dialog.add(new JLabel("Promedio:"));
        dialog.add(txtPromedio);
        dialog.add(new JLabel("Activo? (true/false):"));
        dialog.add(chkActivo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            persona.setNombres(txtNombres.getText());
            persona.setApellidos(txtApellidos.getText());
            persona.setEmail(txtCorreo.getText());

            personaDAO.actualizar(persona);
            refreshTable();

            dialog.dispose();
        });


        dialog.add(btnGuardar);
        dialog.setModal(true);
        dialog.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione una persona para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedUser() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Persona persona = personas.get(row);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + persona.getNombres() + "?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                personaDAO.eliminar(persona.getId());
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una persona para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }



    private void refreshTable() {
        personas = personaDAO.listarPersonas();  // Recargar desde BD
        tableModel.setPersonas(personas);  // Actualizar modelo
        tableModel.fireTableDataChanged();  // Refrescar la tabla
    }


}
