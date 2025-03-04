package com.educationallab.console.dao;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

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

    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE Tipo = 'Estudiante'";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante(
                        rs.getDouble("ID"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Email"),
                        rs.getDouble("Codigo"),
                        null, // Aquí necesitas recuperar el Programa desde BD
                        rs.getBoolean("Activo"),
                        rs.getDouble("Promedio")
                );
                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}
