package com.educationallab.console.dao;

import com.educationallab.console.model.Estudiante;
import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Programa;
import com.educationallab.console.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO extends PersonaDAO {


private Connection conexion;
ProgramaDAO programaDAO = new ProgramaDAO();

    public EstudianteDAO() {
        this.conexion=ConexionBD.getInstancia().getConexion();
    }

    public Estudiante buscarPorId(Double id) {
        String sql = "SELECT * FROM persona WHERE id = ? AND Tipo='Estudiante'";
        Estudiante estudiante = null;

        try (var ps = conexion.prepareStatement(sql)) {

            ps.setDouble(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Programa programa=programaDAO.obtenerProgramaPorId(rs.getDouble("ProgramaID"));
                estudiante = new Estudiante(
                        rs.getDouble("ID"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Email"),
                        rs.getDouble("Codigo"),
                        programa,
                        rs.getBoolean("Activo"),
                        rs.getDouble("Promedio")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiante;
    }


    public Estudiante buscarPorCodigo(Double codigo) {
        String sql = "SELECT * FROM persona WHERE Codigo = ? AND Tipo='Estudiante'";
        Estudiante estudiante = null;

        try (var ps = conexion.prepareStatement(sql)) {

            ps.setDouble(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Programa programa=programaDAO.obtenerProgramaPorId(rs.getDouble("ProgramaID"));
                estudiante = new Estudiante(
                        rs.getDouble("ID"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Email"),
                        rs.getDouble("Codigo"),
                        programa,
                        rs.getBoolean("Activo"),
                        rs.getDouble("Promedio")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiante;
    }

    public boolean insertar(Persona persona) {

        if (!(persona instanceof Estudiante)) {
            throw new IllegalArgumentException("El objeto no es un Profesor");
        }

        Estudiante estudiante = (Estudiante) persona;
        String sql = "INSERT INTO Persona (Nombres, Apellidos, Email, Tipo, Codigo,Promedio,Activo,ProgramaID) " +
                "VALUES (?, ?, ?, 'Estudiante', ?,?,?,?)";

        try (var ps = conexion.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getEmail());
            ps.setDouble(4, estudiante.getCodigo());
            ps.setDouble(5, estudiante.getPromedio());
            ps.setBoolean(6, estudiante.getActivo());
            ps.setDouble(7, estudiante.getPrograma().getId());

            ps.executeUpdate();
            System.out.println("Estudiante insertado correctamente.");
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Persona> listar() {
        List<Persona> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE Tipo='Estudiante'";

        try (var stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Programa programa=programaDAO.obtenerProgramaPorId(rs.getDouble("ProgramaID"));
                Estudiante estudiante = new Estudiante(

                        rs.getDouble("ID"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Email"),
                        rs.getDouble("Codigo"),
                        programa,
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


    public void actualizar(Persona persona) {
        if (!(persona instanceof Estudiante)) {
            throw new IllegalArgumentException("El objeto no es un Estudiante");
        }

        Estudiante estudiante = (Estudiante) persona;


        String sql = "UPDATE Persona SET Nombres = ?, Apellidos = ?, Email = ?, Codigo = ?, ProgramaID = ?, Activo = ?, Promedio = ? WHERE ID = ?";
        try (var ps = conexion.prepareStatement(sql)) {
            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getEmail());
            ps.setDouble(4, estudiante.getCodigo());
            ps.setDouble(5, estudiante.getPrograma().getId());
            ps.setBoolean(6, estudiante.getActivo());
            ps.setDouble(7, estudiante.getPromedio());
            ps.setDouble(8, estudiante.getId());

            ps.executeUpdate();
            System.out.println("Profesor actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean eliminar(Double id) {
        String sql = "DELETE FROM Persona WHERE id = ?";
        try (var ps = conexion.prepareStatement(sql)) {

            ps.setDouble(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Estudiante con ID " + id + " eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un estudiante con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarPorCodigo(Double codigo) {
        String sql = "DELETE FROM Persona WHERE Codigo = ?";
        try (var ps = conexion.prepareStatement(sql)) {

            ps.setDouble(1, codigo);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Estudiante con ID " + codigo + " eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró un estudiante con ID " + codigo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
