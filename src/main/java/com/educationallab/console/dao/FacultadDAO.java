package com.educationallab.console.dao;
import com.educationallab.console.model.Facultad;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadDAO {

    private Connection conexion;


    public FacultadDAO() {
        this.conexion = ConexionBD.getInstancia().getConexion();
    }



    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Facultad (ID, Nombre, DecanoID) VALUES " +
                "(1.0, 'Facultad de Ingeniería', 1.0), " +
                "(2.0, 'Facultad de Ciencias', 1.0), " +
                "(3.0, 'Facultad de Humanidades', 1.0)";

        try (var stmt = conexion.prepareStatement(sql)) {
            stmt.executeUpdate();
            System.out.println("Datos semilla insertados en Facultad.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Facultad obtenerFacultad(Double facultadId) {
        Facultad facultad = null;
        String sql = "SELECT * FROM Facultad WHERE ID = ?";
        ProfesorDAO profesorDAO = new ProfesorDAO();
        try (var stmt = conexion.prepareStatement(sql)) {
            stmt.setDouble(1, facultadId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Profesor profesor = profesorDAO.buscarPorId(rs.getDouble("DecanoId"));
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

        try (var stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Facultad facultad = new Facultad(
                        rs.getDouble("ID"),
                        rs.getString("Nombre"),
                        null
                );
                facultades.add(facultad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultades;
    }


}
