package com.educationallab.console.dao;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM Persona";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if (rs.getString("TipoContrato") != null) {

                    Profesor profesor = new Profesor(
                            (double) rs.getInt("ID"),
                            rs.getString("Nombres"),
                            rs.getString("Apellidos"),
                            rs.getString("Email"),
                            rs.getString("TipoContrato")
                    );
                    personas.add(profesor);
                } else {

                    Programa programa = obtenerPrograma(rs.getDouble("ProgramaID"), con);
                    Estudiante estudiante = new Estudiante(
                            (double) rs.getDouble("ID"),
                            rs.getString("Nombres"),
                            rs.getString("Apellidos"),
                            rs.getString("Email"),
                            rs.getDouble("Codigo"),
                            programa,
                            rs.getBoolean("Activo"),
                            rs.getDouble("Promedio")
                    );
                    personas.add(estudiante);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    private Programa obtenerPrograma(Double programaId, Connection con) {
        Programa programa = null;
        String sql = "SELECT * FROM Programa WHERE ID = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, programaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    programa = new Programa(
                            rs.getDouble("ID"),
                            rs.getString("Nombre"),
                            rs.getInt("Duracion"),
                            rs.getDate("Registro").toLocalDate(),
                            null
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }



    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Persona ( Nombres, Apellidos, Email,Tipo, TipoContrato) VALUES " +
                "('Juan', 'Pérez', 'juan.perez@universidad.edu','Profesor', 'Titular')";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Datos semilla insertados en Persona (Decano).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO Persona (Nombres, Apellidos, Email, Tipo, Codigo, ProgramaID, Activo, Promedio) " +
                "VALUES (?, ?, ?, 'Estudiante', ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getEmail());
            ps.setDouble(4, estudiante.getCodigo());
            ps.setDouble(5, estudiante.getPrograma().getId());
            ps.setBoolean(6, estudiante.isActivo());
            ps.setDouble(7, estudiante.getPromedio());
            ps.executeUpdate();
            System.out.println("Estudiante insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertar(Profesor profesor) {
        String sql = "INSERT INTO Persona (Nombres, Apellidos, Email, Tipo, TipoContrato) " +
                "VALUES (?, ?, ?, 'Profesor', ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, profesor.getNombres());
            ps.setString(2, profesor.getApellidos());
            ps.setString(3, profesor.getEmail());
            ps.setString(4, profesor.getTipoContrato());

            ps.executeUpdate();
            System.out.println("Profesor insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
