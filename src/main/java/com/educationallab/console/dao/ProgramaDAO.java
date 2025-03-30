package com.educationallab.console.dao;
import com.educationallab.console.model.Facultad;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAO {
    private Connection conexion;

    public ProgramaDAO() {
        this.conexion=ConexionBD.getInstancia().getConexion();
    }


    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Programa (ID, Nombre, Duracion, Registro, FacultadID) VALUES " +
                "(1, 'Ingeniería de Sistemas', 5, '2023-01-01', 1), " +
                "(2, 'Matemáticas', 4, '2022-05-15', 2), " +
                "(3, 'Historia', 4, '2021-08-10', 3)";

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.executeUpdate();
            System.out.println("Datos semilla insertados en Programa.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Programa> listarProgramas() {
        List<Programa> programas = new ArrayList<>();
        String sql = "SELECT * FROM Programa";
        FacultadDAO facultadDAO = new FacultadDAO();

        try (var stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Facultad facultad = facultadDAO.obtenerFacultad(rs.getDouble("FacultadID"));
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

    public Programa obtenerProgramaPorId(Double programaId) {
        Programa programa = null;
        String sql = "SELECT * FROM Programa WHERE ID = ?";
        FacultadDAO facultadDAO = new FacultadDAO();


        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setDouble(1, programaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Facultad facultad = facultadDAO.obtenerFacultad(rs.getDouble("FacultadID"));
                    programa = new Programa(
                            rs.getDouble("ID"),
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
