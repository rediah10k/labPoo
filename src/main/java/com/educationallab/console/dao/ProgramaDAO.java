package com.educationallab.console.dao;
import com.educationallab.console.model.Facultad;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAO {

    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Programa (ID, Nombre, Duracion, Registro, FacultadID) VALUES " +
                "(1, 'Ingeniería de Sistemas', 5, '2023-01-01', 1), " +
                "(2, 'Matemáticas', 4, '2022-05-15', 2), " +
                "(3, 'Historia', 4, '2021-08-10', 3)";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Datos semilla insertados en Programa.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Programa> listarProgramas() {
        List<Programa> programas = new ArrayList<>();
        String sql = "SELECT * FROM Programa";
        FacultadDAO facultadDAO = new FacultadDAO();

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Facultad facultad = facultadDAO.obtenerFacultad(rs.getInt("FacultadID"), con);
                Programa programa = new Programa(
                        rs.getInt("ID"),
                        rs.getString("Nombre"),
                        rs.getInt("Duracion"),
                        rs.getDate("Registro").toLocalDate(),
                        facultad
                );
                programas.add(programa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programas;
    }

    public Programa obtenerProgramaPorId(int programaId) {
        Programa programa = null;
        String sql = "SELECT * FROM Programa WHERE ID = ?";
        FacultadDAO facultadDAO = new FacultadDAO();


        try (Connection con = ConexionBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, programaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Facultad facultad = facultadDAO.obtenerFacultad(rs.getInt("FacultadID"), con);
                    programa = new Programa(
                            rs.getInt("ID"),
                            rs.getString("Nombre"),
                            rs.getInt("Duracion"),
                            rs.getDate("Registro").toLocalDate(),
                            facultad
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }



}
