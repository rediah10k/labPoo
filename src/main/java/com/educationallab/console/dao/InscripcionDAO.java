package com.educationallab.console.dao;
import com.educationallab.console.model.Inscripcion;
import com.educationallab.console.model.Curso;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InscripcionDAO {

    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM Inscripcion";
        PersonaDAO personaDAO = new PersonaDAO();
        CursoDAO cursoDAO = new CursoDAO();
        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            while (rs.next()) {
                Estudiante estudiante = (Estudiante) personaDAO.buscarPersonaPorId(rs.getDouble("EstudianteID"));
                Curso curso = cursoDAO.obtenerCursoPorId(rs.getInt("CursoID"));
                Inscripcion inscripcion = new Inscripcion(
                        curso,
                        rs.getInt("Año"),
                        estudiante,
                        rs.getInt("Semestre")

                );
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }


    public boolean insertar(Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripcion (CursoID, EstudianteID, Semestre, Año) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, inscripcion.getCurso().getId());
            stmt.setDouble(2, inscripcion.getEstudiante().getId());
            stmt.setInt(3, inscripcion.getSemestre());
            stmt.setInt(4, inscripcion.getAnio());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean actualizar(Inscripcion inscripcion) {
        String sql = "UPDATE Inscripcion SET Semestre = ?, Año = ?,CursoID=?,EstudianteId=? WHERE CursoID = ? AND EstudianteID = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, inscripcion.getSemestre());
            stmt.setInt(2, inscripcion.getAnio());
            stmt.setInt(3, inscripcion.getCurso().getId());
            stmt.setDouble(4, inscripcion.getEstudiante().getId());
            stmt.setInt(5, inscripcion.getCurso().getId());
            stmt.setDouble(6, inscripcion.getEstudiante().getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Inscripcion buscarPorId(int cursoId, double estudianteId) {
        String sql = "SELECT * FROM Inscripcion WHERE CursoID = ? AND EstudianteID = ?";
        Inscripcion inscripcion = null;
        PersonaDAO personaDAO = new PersonaDAO();
        CursoDAO cursoDAO = new CursoDAO();
        try (Connection con = ConexionBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, cursoId);
            stmt.setDouble(2, estudianteId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = cursoDAO.obtenerCursoPorId(rs.getInt("CursoID"));
                    Estudiante estudiante = (Estudiante) personaDAO.buscarPersonaPorId(rs.getDouble("EstudianteID"));
                    inscripcion = new Inscripcion(
                            curso,
                            rs.getInt("Anio"),
                            estudiante,
                            rs.getInt("Semestre")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripcion;
    }


    public boolean eliminar(Inscripcion inscripcion) {

        String sql = "DELETE FROM Inscripcion WHERE CursoID = ? AND EstudianteID = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, inscripcion.getCurso().getId());
            stmt.setDouble(2, inscripcion.getEstudiante().getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
