package com.educationallab.console.view.tablemodels;

import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private String[] columnNames = { "Rol", "Nombres", "Apellidos", "Programa", "Tipo de Contrato"};
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

            case 0: if (user instanceof Estudiante) {
                return "Estudiante";
            }
            if (user instanceof Profesor) {
                    return "Docente";
            }
            case 1: return user.getNombres();
            case 2: return user.getApellidos();
            case 3: if (user instanceof Estudiante) {
                return ((Estudiante) user).getPrograma().getNombre();
            }else{
                return "No aplica";
            }
            case 4: if (user instanceof Profesor) {
                return ((Profesor) user).getTipoContrato();
            }else{
                return "No aplica";
            }
            default: return null;
        }
    }
}
