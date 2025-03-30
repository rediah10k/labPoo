package com.educationallab.console.view.tablemodels;

import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private String[] columnNames = { "Nombres", "Apellidos", "Email"};
    @Getter
    @Setter
    private List<Persona> personas;

    public UserTableModel(List<Persona> users) {
        this.personas = users;
    }

    @Override
    public int getRowCount() {
        return personas.size();
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
        Persona user = personas.get(rowIndex);
        switch (columnIndex) {

            case 0: return user.getNombres();
            case 1: return user.getApellidos();
            case 2: return user.getEmail();

            default: return null;
        }
    }
}
