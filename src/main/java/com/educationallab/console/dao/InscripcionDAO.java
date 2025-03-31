package com.educationallab.console.dao;
import com.educationallab.console.model.Inscripcion;
import com.educationallab.console.model.Curso;
import com.educationallab.console.model.Estudiante;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InscripcionDAO {
    private Connection conexion;

    public InscripcionDAO() {
        this.conexion=ConexionBD.getInstancia().getConexion();
    }

    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM Inscripcion";
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        CursoDAO cursoDAO = new CursoDAO();

        try (var stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                Estudiante estudiante = estudianteDAO.buscarPorId(rs.getDouble("EstudianteID"));
                Curso curso = cursoDAO.obtenerCursoPorId(rs.getInt("CursoID"));
                Inscripcion inscripcion = new Inscripcion(
                        rs.getDouble("Id"),
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

    public List<Inscripcion> listarInscripcionesPorEstudiante(double estudianteId) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM Inscripcion WHERE EstudianteID = ?";
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        CursoDAO cursoDAO = new CursoDAO();

        try (var stmt = conexion.prepareStatement(sql)) {
            stmt.setDouble(1, estudianteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Estudiante estudiante = estudianteDAO.buscarPorId(rs.getDouble("EstudianteID"));
                    Curso curso = cursoDAO.obtenerCursoPorId(rs.getInt("CursoID"));

                    Inscripcion inscripcion = new Inscripcion(
                            rs.getDouble("Id"),
                            curso,
                            rs.getInt("Año"),
                            estudiante,
                            rs.getInt("Semestre")
                    );
                    inscripciones.add(inscripcion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }


    public boolean insertar(Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripcion (CursoID, EstudianteID, Semestre, Año) VALUES (?, ?, ?, ?)";

        try (var stmt = conexion.prepareStatement(sql);) {

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
        String sql = "UPDATE Inscripcion SET Semestre = ?, Año = ?,CursoID=?,EstudianteID=? WHERE ID=?";

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setInt(1, inscripcion.getSemestre());
            stmt.setInt(2, inscripcion.getAnio());
            stmt.setInt(3, inscripcion.getCurso().getId());
            stmt.setDouble(4, inscripcion.getEstudiante().getId());
            stmt.setDouble(5,inscripcion.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Inscripcion buscarPorId(double id) {

        EstudianteDAO estudianteDAO = new EstudianteDAO();
        CursoDAO cursoDAO = new CursoDAO();
        String sql = "SELECT * FROM Inscripcion WHERE ID=?";
        Inscripcion inscripcion = null;

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setDouble(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = cursoDAO.obtenerCursoPorId(rs.getInt("CursoID"));
                    Estudiante estudiante = estudianteDAO.buscarPorId(rs.getDouble("EstudianteID"));
                    inscripcion = new Inscripcion(
                            rs.getDouble("Id"),
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

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setInt(1, inscripcion.getCurso().getId());
            stmt.setDouble(2, inscripcion.getEstudiante().getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
