package com.educationallab.console.view;

import com.educationallab.console.controller.ProfesoresController;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;

import javax.swing.*;
import java.awt.*;

public class DocentesView extends JPanel {

    private ProfesoresController controller;
    private JTextField txtId;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JTextField txtTipoContrato;
    private JComboBox<Programa> cmbPrograma;

    private JButton btnEliminar;
    private JButton btnGuardar;
    private JButton btnBuscar;


    public void setController(ProfesoresController controller) {
        this.controller = controller;

    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de Profesores"));

        JPanel campos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;


        campos.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        JPanel panelId = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        txtId = new JTextField(10);
        btnBuscar = new JButton("Buscar");
        panelId.add(txtId);
        panelId.add(btnBuscar);
        campos.add(panelId, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;
        txtNombres = new JTextField(15);
        campos.add(txtNombres, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        txtApellidos = new JTextField(15);
        campos.add(txtApellidos, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        campos.add(txtEmail, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Tipo de Contrato:"), gbc);
        gbc.gridx = 1;
        txtTipoContrato = new JTextField(15);
        campos.add(txtTipoContrato, gbc);


        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        botones.add(btnEliminar);
        botones.add(btnGuardar);


        add(campos, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);


        btnBuscar.addActionListener(e -> buscarProfesor(txtId));
    }


    public DocentesView() {
        initComponents();
        setController(new ProfesoresController());
        configurarListeners();
    }


    private void configurarListeners() {
        btnGuardar.addActionListener(e -> guardarProfesor());
        btnEliminar.addActionListener(e -> eliminarProfesor());
    }


    private void buscarProfesor(JTextField txtId) {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Controlador no configurado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Double idStr = Double.parseDouble(txtId.getText());

        try {

            Profesor profesor = controller.buscarPorId(idStr);

            if (profesor != null) {
                cargarProfesor(profesor);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el Profesor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }




    private void guardarProfesor() {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Controlador no configurado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String email = txtEmail.getText().trim();
        String tipoContrato = txtTipoContrato.getText().trim();


        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || tipoContrato.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Profesor profesor = new Profesor();
        profesor.setNombres(nombre);
        profesor.setApellidos(apellidos);
        profesor.setEmail(apellidos);
        profesor.setTipoContrato(tipoContrato);


        if (controller.agregarProfesor(profesor)) {
            JOptionPane.showMessageDialog(this, "Profesor guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el Profesor", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProfesor() {
        if (controller == null) return;

        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un Profesor para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double id = Double.parseDouble(txtId.getText());
            if (controller.eliminarProfesor(id)) {
                JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el Profesor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarProfesor(Profesor profesor) {
        txtId.setText(String.valueOf(profesor.getId()));
        txtNombres.setText(profesor.getNombres());
        txtApellidos.setText(profesor.getApellidos());
        txtEmail.setText(profesor.getEmail());
        txtTipoContrato.setText(profesor.getTipoContrato());


    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtTipoContrato.setText("");
    }
}
