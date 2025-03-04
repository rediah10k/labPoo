package com.educationallab.console.dao;
import com.educationallab.console.model.Facultad;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDAO {

    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Facultad (ID, Nombre, DecanoID) VALUES " +
                "(1, 'Facultad de Ingeniería', 1), " +
                "(2, 'Facultad de Ciencias', 1), " +
                "(3, 'Facultad de Humanidades', 1)";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Datos semilla insertados en Facultad.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Facultad obtenerFacultad(Double facultadId, Connection con) {
        Facultad facultad = null;
        String sql = "SELECT * FROM Facultad WHERE ID = ?";
        PersonaDAO personaDAO = new PersonaDAO();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, facultadId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Profesor profesor = (Profesor) personaDAO.buscarPersonaPorId(rs.getDouble("DecanoId"));
                    facultad = new Facultad(
                            rs.getDouble("ID"),
                            rs.getString("Nombre"),
                            profesor
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultad;
    }

    public List<Facultad> listarFacultades() {
        List<Facultad> facultades = new ArrayList<>();
        String sql = "SELECT * FROM Facultad";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Facultad facultad = new Facultad(
                        rs.getDouble("ID"),
                        rs.getString("Nombre"),
                        null // Persona responsable (podría recuperarse si se requiere)
                );
                facultades.add(facultad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultades;
    }


}
