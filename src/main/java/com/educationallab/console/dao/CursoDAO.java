package com.educationallab.console.dao;

import com.educationallab.console.model.Curso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;

public class CursoDAO {
    private Connection conexion;


    public CursoDAO() {
        this.conexion = ConexionBD.getInstancia().getConexion();
    }

    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Curso (ID, Nombre, ProgramaID, Activo) VALUES " +
                "(1, 'Matemáticas Básicas', 2, true), " +
                "(2, 'Estructuras de Datos', 1, true), " +
                "(3, 'Historia Contemporánea', 3, true)";

        try (var stmt = conexion.prepareStatement(sql)) {

            stmt.executeUpdate();
            System.out.println("Datos semilla insertados en Curso.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertar(Curso curso) {
        String sql="INSERT INTO Curso (Nombre,ProgramaID,Activo) VALUES(?,?,?,?)";
        try (var stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre());
            stmt.setDouble(2, curso.getPrograma().getId());
            stmt.setBoolean(3, curso.isActivo());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        ProgramaDAO programaDAO = new ProgramaDAO();

        try (var stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Programa programa = programaDAO.obtenerProgramaPorId(rs.getDouble("ProgramaID"));
                Curso curso = new Curso(
                        rs.getInt("ID"),
                        programa,
                        rs.getString("Nombre"),
                        rs.getBoolean("Activo")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }







    public Curso obtenerCursoPorId(int cursoId) {
        String sql = "SELECT * FROM Curso WHERE ID = ?";
        Curso curso = null;
        ProgramaDAO programaDAO = new ProgramaDAO();

        try (var stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, cursoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Programa programa = programaDAO.obtenerProgramaPorId(rs.getDouble("ProgramaID"));
                    curso = new Curso(
                            rs.getInt("ID"),
                            programa,
                            rs.getString("Nombre"),
                            rs.getBoolean("Activo")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

}

