package com.educationallab.console.view;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EstudianteDetalleView extends JPanel {


    private JTextField txtCodigo;
    private JLabel lblNombre, lblInfoAdicional;
    private JTabbedPane tabbedPane;
    private JTable tablaCursos, tablaDocentes, tablaHistorial;
    private JButton btnBuscar, btnInscribir;
    private JComboBox<String> cmbPeriodo;


    private void inicializarComponentes() {

        JPanel panelBusqueda = crearPanelBusqueda();
        add(panelBusqueda, BorderLayout.NORTH);

        // Panel central con pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Cursos", crearPanelCursos());
        tabbedPane.addTab("Docentes", crearPanelDocentes());
        tabbedPane.addTab("Historial", crearPanelHistorial());
        tabbedPane.addTab("Inscribir", crearPanelInscripcion());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Búsqueda de Estudiante"));

        txtCodigo = new JTextField();
        btnBuscar = new JButton("Buscar");
        lblNombre = new JLabel("Nombre: ");
        lblInfoAdicional = new JLabel("Info adicional: ");

        panel.add(new JLabel("Código Estudiante:"));
        panel.add(txtCodigo);
        panel.add(btnBuscar);
        panel.add(new JLabel());
        panel.add(lblNombre);
        panel.add(lblInfoAdicional);

        return panel;
    }

    private JPanel crearPanelCursos() {
        tablaCursos = new JTable();
        return crearPanelTabla("Listado de Cursos", tablaCursos);
    }

    private JPanel crearPanelDocentes() {
        tablaDocentes = new JTable();
        return crearPanelTabla("Listado de Docentes", tablaDocentes);
    }

    private JPanel crearPanelHistorial() {
        tablaHistorial = new JTable();
        return crearPanelTabla("Historial de Inscripciones", tablaHistorial);
    }

    private JPanel crearPanelInscripcion() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Nueva Inscripción"));

        cmbPeriodo = new JComboBox<>(new String[]{"2023-1", "2023-2"});
        JTextField txtAño = new JTextField();
        JTextField txtConsola = new JTextField();
        JTextField txtDocente = new JTextField();
        btnInscribir = new JButton("Inscribir Curso");

        panel.add(new JLabel("Periodo:"));
        panel.add(cmbPeriodo);
        panel.add(new JLabel("Año:"));
        panel.add(txtAño);
        panel.add(new JLabel("Consola:"));
        panel.add(txtConsola);
        panel.add(new JLabel("Docente:"));
        panel.add(txtDocente);
        panel.add(new JLabel());
        panel.add(btnInscribir);

        return panel;
    }

    private JPanel crearPanelTabla(String titulo, JTable tabla) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    // Métodos para acceder a los componentes desde el controlador
    public void addBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void addInscribirListener(ActionListener listener) {
        btnInscribir.addActionListener(listener);
    }

    public String getCodigoEstudiante() {
        return txtCodigo.getText();
    }

    public void actualizarInfoEstudiante(String nombre, String info) {
        lblNombre.setText("Nombre: " + nombre);
        lblInfoAdicional.setText("Info: " + info);
    }

    public void actualizarTablaHistorial(TableModel model) {
        tablaHistorial.setModel(model);
    }


    public EstudianteDetalleView() {
        setLayout(new BorderLayout(10, 10));
        inicializarComponentes();
    }
}
