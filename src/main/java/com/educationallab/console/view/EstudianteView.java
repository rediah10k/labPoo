package com.educationallab.console.view;

import com.educationallab.console.controller.CursoController;
import com.educationallab.console.controller.EstudiantesController;
import com.educationallab.console.controller.ProfesoresController;
import com.educationallab.console.model.Curso;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EstudianteView extends JPanel {

    private EstudiantesController controller;
    private JTextField txtCodigo;
    private JTextField txtPromedio;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtEmail;
    private JComboBox<Programa> cmbPrograma;
    private JCheckBox checkActivo;

    private JButton btnEliminar;
    private JButton btnGuardar;
    private JButton btnBuscar;


    public void setController(EstudiantesController controller) {
        this.controller = controller;
        cargarProgramas();

    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de Estudiantes"));

        JPanel campos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        campos.add(new JLabel("Código:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JPanel panelId = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.insets = new Insets(0, 0, 0, 0);
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        gbcPanel.weightx = 1.0;

        txtCodigo = new JTextField(10);
        panelId.add(txtCodigo, gbcPanel);

        gbcPanel.gridx = 1;
        gbcPanel.weightx = 0;
        btnBuscar = new JButton("Buscar");
        panelId.add(btnBuscar, gbcPanel);

        campos.add(panelId, gbc);


        gbc.gridx = 0;
        gbc.gridy+=2;
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
        campos.add(new JLabel("Programa:"), gbc);
        gbc.gridx = 1;
        cmbPrograma = new JComboBox<>();
        campos.add(cmbPrograma, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Promedio:"), gbc);
        gbc.gridx = 1;
        txtPromedio = new JTextField(15);
        campos.add(txtPromedio, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Activo?:"), gbc);
        gbc.gridx = 1;
        checkActivo = new JCheckBox();
        campos.add(checkActivo, gbc);


        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        botones.add(btnEliminar);
        botones.add(btnGuardar);

        add(campos, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarEstudiante(txtCodigo));
    }


    private void cargarProgramas() {
        if (controller != null) {
            List<Programa> programas = controller.obtenerProgramas();
            DefaultComboBoxModel<Programa> model = new DefaultComboBoxModel<>();
            for (Programa programa : programas) {
                model.addElement(programa);
            }
            cmbPrograma.setModel(model);

            cmbPrograma.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value,
                                                              int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Programa) {
                        setText(((Programa) value).getNombre());
                    }
                    return this;
                }
            });
        }
    }




    public EstudianteView() {
        initComponents();
        setController(new EstudiantesController());
        configurarListeners();
    }


    private void configurarListeners() {
        btnGuardar.addActionListener(e -> guardarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
    }

    private void buscarEstudiante(JTextField txtCodigo) {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Controlador no configurado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Double codigo = Double.parseDouble(txtCodigo.getText());

        try {
            Estudiante estudiante = controller.buscarPorCodigo(codigo);

            if (estudiante != null) {
                cargarEstudiante(estudiante);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el Estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Codigo inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    private void guardarEstudiante() {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Controlador no configurado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String email = txtEmail.getText().trim();
        String codigo = txtCodigo.getText().trim();
        Programa programa = (Programa) cmbPrograma.getSelectedItem();
        String promedio = txtPromedio.getText().trim();
        boolean activo = checkActivo.isSelected();


        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() ||
                codigo.isEmpty() || promedio.isEmpty() || programa == null ) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setNombres(nombre);
        estudiante.setApellidos(apellidos);
        estudiante.setEmail(email);
        estudiante.setPrograma(programa);
        estudiante.setActivo(activo);
        estudiante.setCodigo(Double.valueOf(codigo));
        estudiante.setPromedio(Double.valueOf(promedio));

        if (controller.agregarEstudiante(estudiante)) {
            JOptionPane.showMessageDialog(this, "Estudiante guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el Estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEstudiante() {
        if (controller == null) return;

        if (txtCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un Estudiante para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Double codigo = Double.parseDouble(txtCodigo.getText());
            if (controller.eliminarEstudiantePorCodigo(codigo)) {
                JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el Estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Codigo inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarEstudiante(Estudiante estudiante) {
        txtCodigo.setText(String.valueOf(estudiante.getCodigo()));
        txtNombres.setText(estudiante.getNombres());
        txtApellidos.setText(estudiante.getApellidos());
        txtEmail.setText(estudiante.getEmail());
        txtPromedio.setText(estudiante.getPromedio().toString());
        for (int i = 0; i < cmbPrograma.getItemCount(); i++) {
            if (cmbPrograma.getItemAt(i).getId() == estudiante.getPrograma().getId()) {
                cmbPrograma.setSelectedIndex(i);
                break;
            }
        }
        checkActivo.setSelected(estudiante.isActivo());

    }

    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        txtPromedio.setText("");
        cmbPrograma.setSelectedIndex(0);
        checkActivo.setSelected(false);
    }
}
