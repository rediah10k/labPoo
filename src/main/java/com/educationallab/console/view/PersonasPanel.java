package com.educationallab.console.view;

import com.educationallab.console.controller.EstudiantesController;
import com.educationallab.console.controller.ProfesoresController;
import com.educationallab.console.model.*;
import com.educationallab.console.view.tablemodels.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PersonasPanel extends JPanel {
    private JTable table;
    private UserTableModel tableModel;
    private EstudiantesController estudiantesController;
    private ProfesoresController profesoresController;

    public PersonasPanel() {
        this.setLayout(new BorderLayout());
        tableModel = new UserTableModel(List.of());
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        Persona persona = tableModel.getPersonas().get(row);
                        mostrarInfoPersona(persona);
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
        btnEliminar.addActionListener(e -> eliminarPersonaSeleccionada());

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(e -> mostrarDialogoEditar());

        panelBotones.add(btnInscribir);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnEditar);
        this.add(panelBotones, BorderLayout.NORTH);
    }

    public void setEstudiantesController(EstudiantesController controller) {
        this.estudiantesController = controller;
    }

    public void setProfesoresController(ProfesoresController controller) {
        this.profesoresController = controller;
        actualizarTabla();

    }

    public void actualizarTabla() {
        List<Persona> personas=estudiantesController.obtenerEstudiantes();
        List<Persona> profesores=profesoresController.obtenerProfesores();
        personas.addAll(profesores);
        tableModel.setPersonas(personas);
        tableModel.fireTableDataChanged();
    }

    private void mostrarInfoPersona(Persona persona) {
        JOptionPane.showMessageDialog(this,
                "ID: " + persona.getId() + "\n" +
                        "Nombres: " + persona.getNombres() + "\n" +
                        "Apellidos: " + persona.getApellidos() + "\n" +
                        "Correo: " + persona.getEmail(),
                "Información de Persona", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarDialogoAgregar() {
        JTextField txtNombres = new JTextField();
        JTextField txtApellidos = new JTextField();
        JTextField txtCorreo = new JTextField();
        JComboBox<String> comboTipoPersona = new JComboBox<>(new String[]{"Estudiante", "Profesor"});
        JTextField txtTipoContrato = new JTextField();
        JComboBox<Programa> comboPrograma = new JComboBox<>(estudiantesController.obtenerProgramas().toArray(new Programa[0]));
        JTextField txtCodigo = new JTextField();
        JTextField txtPromedio = new JTextField();
        JCheckBox chkActivo = new JCheckBox();

        Object[] inputs = {"Nombres:", txtNombres, "Apellidos:", txtApellidos, "Correo:", txtCorreo,
                "Tipo de usuario:", comboTipoPersona, "Tipo de contrato:", txtTipoContrato,
                "Programa:", comboPrograma, "Codigo:", txtCodigo, "Promedio:", txtPromedio,
                "Activo?", chkActivo};

        int result = JOptionPane.showConfirmDialog(this, inputs, "Agregar Persona", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String tipoPersona = (String) comboTipoPersona.getSelectedItem();
            if ("Estudiante".equals(tipoPersona)) {
                estudiantesController.agregarEstudiante(
                        (String) comboTipoPersona.getSelectedItem(),
                        txtNombres.getText(), txtApellidos.getText(), txtCorreo.getText(),
                        (Programa) comboPrograma.getSelectedItem(), Double.valueOf(txtPromedio.getText()),
                        Double.valueOf(txtPromedio.getText()),
                        chkActivo.isSelected()
                );
            } else if ("Profesor".equals(tipoPersona)) {
                profesoresController.agregarProfesor(
                        txtNombres.getText(), txtApellidos.getText(), txtCorreo.getText(), txtTipoContrato.getText()
                );
            }
            actualizarTabla();
        }
    }

    private void mostrarDialogoEditar() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Persona persona = tableModel.getPersonas().get(row);

            JTextField txtNombres = new JTextField(persona.getNombres());
            JTextField txtApellidos = new JTextField(persona.getApellidos());
            JTextField txtCorreo = new JTextField(persona.getEmail());
            JComboBox<String> comboTipoPersona = new JComboBox<>(new String[]{"Estudiante", "Profesor"});
            JTextField txtTipoContrato = new JTextField();
            JComboBox<Programa> comboPrograma = new JComboBox<>(estudiantesController.obtenerProgramas().toArray(new Programa[0]));
            JTextField txtCodigo = new JTextField();
            JTextField txtPromedio = new JTextField();
            JCheckBox chkActivo = new JCheckBox();

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

            Object[] inputs = {"Nombres:", txtNombres, "Apellidos:", txtApellidos, "Correo:", txtCorreo,
                    "Tipo de usuario:", comboTipoPersona, "Tipo de contrato:", txtTipoContrato,
                    "Programa:", comboPrograma, "Codigo:", txtCodigo, "Promedio:", txtPromedio,
                    "Activo?", chkActivo};

            int result = JOptionPane.showConfirmDialog(this, inputs, "Editar Persona", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                if (persona instanceof Estudiante) {
                    estudiantesController.editarEstudiante(
                            persona.getId(), txtNombres.getText(), txtApellidos.getText(), txtCorreo.getText(),
                            Double.valueOf(txtPromedio.getText()), Double.valueOf(txtCodigo.getText()),
                            chkActivo.isSelected(), (Programa) comboPrograma.getSelectedItem()
                    );
                } else if (persona instanceof Profesor) {
                    profesoresController.editarProfesor(
                            persona.getId(), txtNombres.getText(), txtApellidos.getText(), txtCorreo.getText(), txtTipoContrato.getText()
                    );
                }
                actualizarTabla();
            }
        }
    }

    private void eliminarPersonaSeleccionada() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Persona persona = tableModel.getPersonas().get(row);
            if (persona instanceof Estudiante) {
                estudiantesController.eliminarEstudiante((Estudiante) persona);
            } else if (persona instanceof Profesor) {
                profesoresController.eliminarProfesor((Profesor) persona);
            }
            actualizarTabla();
        }
    }
}
