package com.educationallab.console.view.tablemodels;

import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private String[] columnNames = {"Id", "Rol", "Nombres", "Apellidos", "Programa", "Tipo de Contrato"};
    private List<Persona> users;

    public UserTableModel(List<Persona> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
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
        Persona user = users.get(rowIndex);
        switch (columnIndex) {
            case 0: return user.getId();
            case 1: if (user instanceof Estudiante) {
                return "Estudiante";
            }
            if (user instanceof Profesor) {
                    return "Docente";
            }
            case 2: return user.getNombres();
            case 3: return user.getApellidos();
            case 4: if (user instanceof Estudiante) {
                return ((Estudiante) user).getPrograma().getNombre();
            }else{
                return "No aplica";
            }
            case 5: if (user instanceof Profesor) {
                return ((Profesor) user).getTipoContrato();
            }else{
                return "No aplica";
            }
            default: return null;
        }
    }
}
