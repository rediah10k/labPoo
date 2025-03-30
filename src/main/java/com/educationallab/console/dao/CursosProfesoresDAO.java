package com.educationallab.console.dao;

import com.educationallab.console.model.*;
import com.educationallab.console.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesoresDAO {

    private Connection conexion;

    public CursosProfesoresDAO() {
        this.conexion=ConexionBD.getInstancia().getConexion();
    }

    public List<CursoProfesor> listarTodos() {
        List<CursoProfesor> cursosProfesores = new ArrayList<>();
        String sql = "SELECT * FROM CursoProfesor";

        try (
                PreparedStatement stmt = conexion.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            ProfesorDAO profesorDAO = new ProfesorDAO();
            CursoDAO cursoDAO = new CursoDAO();

            while (rs.next()) {
                Profesor profesor = profesorDAO.buscarPorId(rs.getDouble("ProfesorID"));
                Curso curso = cursoDAO.obtenerCursoPorId(rs.getInt("CursoID"));

                CursoProfesor cursoProfesor = new CursoProfesor(
                        rs.getDouble("ID"),
                        curso,
                        rs.getInt("Año"),
                        profesor,
                        rs.getInt("Semestre")
                );
                cursosProfesores.add(cursoProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursosProfesores;
    }


    public boolean insertar(CursoProfesor cursoProfesor) {
        String sql = "INSERT INTO CursoProfesor (ProfesorID, CursoID, AÑO, Semestre) VALUES (?, ?, ?, ?)";

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setDouble(1, cursoProfesor.getProfesor().getId());
            stmt.setInt(2, cursoProfesor.getCurso().getId());
            stmt.setInt(3, cursoProfesor.getAnio());
            stmt.setInt(4, cursoProfesor.getSemestre());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(CursoProfesor cursoProfesor) {
        String sql = "UPDATE CursoProfesor SET Año = ?, Semestre = ?, ProfesorID = ?, CursoID = ? " +
                     "WHERE ID=?";

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setInt(1, cursoProfesor.getAnio());
            stmt.setInt(2, cursoProfesor.getSemestre());
            stmt.setDouble(3, cursoProfesor.getProfesor().getId());
            stmt.setInt(4, cursoProfesor.getCurso().getId());
            stmt.setDouble(5, cursoProfesor.getId());


            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(CursoProfesor cursoProfesor) {
        String sql = "DELETE FROM CursoProfesor WHERE ID=?";

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.setDouble(1, cursoProfesor.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
