package com.educationallab.console.view;

import com.educationallab.console.controller.CursoController;
import com.educationallab.console.model.Curso;
import com.educationallab.console.model.Programa;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CursoView extends JPanel {

    private CursoController controller;
    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox<Programa> cmbPrograma;
    private JButton btnEliminar;
    private JButton btnGuardar;
    private JButton btnBuscar;

    public void setController(CursoController controller) {
        this.controller = controller;
        cargarProgramas();
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

    public CursoView() {
        initComponents();
        setController(new CursoController());
        configurarListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Gestión de Cursos"));

        JPanel campos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;


        campos.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;

        JPanel panelId = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel.gridx = 0;
        gbcPanel.weightx = 1.0;

        txtId = new JTextField(10);
        panelId.add(txtId, gbcPanel);

        gbcPanel.gridx = 1;
        gbcPanel.weightx = 0;
        btnBuscar = new JButton("Buscar");
        panelId.add(btnBuscar, gbcPanel);

        campos.add(panelId, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        campos.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        campos.add(new JLabel("Programa:"), gbc);
        gbc.gridx = 1;
        cmbPrograma = new JComboBox<>();
        campos.add(cmbPrograma, gbc);


        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnEliminar = new JButton("Eliminar");
        btnGuardar = new JButton("Guardar");
        botones.add(btnEliminar);
        botones.add(btnGuardar);


        add(campos, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);


        btnBuscar.addActionListener(e -> buscarCurso(txtId));
    }


    private void buscarCurso(JTextField txtId) {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Controlador no configurado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idStr = Integer.parseInt(txtId.getText());

            try {

                Curso curso = controller.buscarPorId(idStr);

                if (curso != null) {
                    cargarCurso(curso);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el curso", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }

    }



    private void configurarListeners() {
        btnGuardar.addActionListener(e -> guardarCurso());
        btnEliminar.addActionListener(e -> eliminarCurso());
    }

    private void guardarCurso() {
        if (controller == null) {
            JOptionPane.showMessageDialog(this, "Controlador no configurado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText().trim();
        Programa programa = (Programa) cmbPrograma.getSelectedItem();

        if (nombre.isEmpty() || programa == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setPrograma(programa);


        if (controller.guardar(curso)) {
            JOptionPane.showMessageDialog(this, "Curso guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el curso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCurso() {
        if (controller == null) return;

        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            if (controller.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el curso", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarCurso(Curso curso) {
        txtId.setText(String.valueOf(curso.getId()));
        txtNombre.setText(curso.getNombre());

        for (int i = 0; i < cmbPrograma.getItemCount(); i++) {
            if (cmbPrograma.getItemAt(i).getId() == curso.getPrograma().getId()) {
                cmbPrograma.setSelectedIndex(i);
                break;
            }
        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        cmbPrograma.setSelectedIndex(0);
    }
}