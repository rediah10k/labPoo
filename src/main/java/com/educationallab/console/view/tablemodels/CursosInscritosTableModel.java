package com.educationallab.console.view.tablemodels;

import com.educationallab.console.model.Inscripcion;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;


@Setter
@Getter

public class CursosInscritosTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nombres", "Apellidos", "Curso", "Semestre","Año"};
    protected List<Inscripcion> inscripciones;

    public CursosInscritosTableModel(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    @Override
    public int getRowCount() {
        return inscripciones.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Inscripcion inscripcion = inscripciones.get(rowIndex);
        switch (columnIndex) {
            case 0: return inscripcion.getEstudiante().getNombres();
            case 1: return inscripcion.getEstudiante().getApellidos();
            case 2: return inscripcion.getCurso().getNombre();
            case 3: return inscripcion.getSemestre();
            case 4: return inscripcion.getAnio();
            default: return null;
        }
    }
}
