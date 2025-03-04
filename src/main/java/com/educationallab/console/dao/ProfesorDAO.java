package com.educationallab.console.dao;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {

    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Persona (ID, Nombres, Apellidos, Email, TipoContrato) VALUES " +
                "(1, 'Juan', 'Pérez', 'juan.perez@universidad.edu', 'Titular')";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Datos semilla insertados en Persona (Decano).");

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

    public List<Profesor> listar() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE Tipo = 'Profesor'";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Profesor profesor = new Profesor(
                        rs.getDouble("ID"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Email"),
                        rs.getString("TipoContrato")
                );
                profesores.add(profesor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesores;
    }
}
