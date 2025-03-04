package com.educationallab.console.view.tablemodels;

import com.educationallab.console.model.CursoProfesor;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CursosProfesoresTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nombres", "Apellidos", "Curso", "Semestre","Año"};
    private List<CursoProfesor> cursosProfesores;

    public CursosProfesoresTableModel(List<CursoProfesor> cursoProfesor) {
        this.cursosProfesores = cursoProfesor;
    }

    @Override
    public int getRowCount() {
        return cursosProfesores.size();
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
        CursoProfesor cursoProfesor = cursosProfesores.get(rowIndex);
        switch (columnIndex) {
            case 0: return cursoProfesor.getProfesor().getNombres();
            case 1: return cursoProfesor.getProfesor().getApellidos();
            case 2: return cursoProfesor.getCurso().getNombre();
            case 3: return cursoProfesor.getSemestre();
            case 4: return cursoProfesor.getAnio();
            default: return null;
        }
    }
}
