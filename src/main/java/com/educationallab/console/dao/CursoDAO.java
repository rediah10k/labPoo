package com.educationallab.console.dao;

import com.educationallab.console.model.Curso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.educationallab.console.util.ConexionBD;
import java.sql.*;

public class CursoDAO {

    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Curso (ID, Nombre, ProgramaID, Activo) VALUES " +
                "(1, 'Matemáticas Básicas', 2, true), " +
                "(2, 'Estructuras de Datos', 1, true), " +
                "(3, 'Historia Contemporánea', 3, true)";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Datos semilla insertados en Curso.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

