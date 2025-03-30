package com.educationallab.console.dao;

import com.educationallab.console.model.Persona;
import com.educationallab.console.model.Profesor;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO extends PersonaDAO {

    private Connection conexion;

    public ProfesorDAO() {
        this.conexion=ConexionBD.getInstancia().getConexion();
    }


    public Profesor buscarPorId(Double id) {
        String sql = "SELECT * FROM Persona WHERE id = ?";
        Profesor profesor = null;

        try (var ps = conexion.prepareStatement(sql)) {

            ps.setDouble(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                profesor = new Profesor(
                        rs.getDouble("ID"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("Email"),
                        rs.getString("TipoContrato")
                );


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesor;
    }


    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Persona ( Nombres, Apellidos, Email,Tipo, TipoContrato) VALUES " +
                "('Juan', 'Pérez', 'juan.perez@universidad.edu','Profesor', 'Titular')";

        try (var stmt = conexion.prepareStatement(sql);) {

            stmt.executeUpdate();
            System.out.println("Datos semilla insertados en Persona (Decano).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertar(Persona persona) {

        if (!(persona instanceof Profesor)) {
            throw new IllegalArgumentException("El objeto no es un Profesor");
        }

        Profesor profesor = (Profesor) persona;
        String sql = "INSERT INTO Persona (Nombres, Apellidos, Email, Tipo, TipoContrato) " +
                "VALUES (?, ?, ?, 'Profesor', ?)";

        try (var ps = conexion.prepareStatement(sql);) {

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

    public List<Persona> listar() {
        List<Persona> profesores = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE Tipo='Profesor'";

        try (var stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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


    public void actualizar(Persona persona) {
        if (!(persona instanceof Profesor)) {
            throw new IllegalArgumentException("El objeto no es un Profesor");
        }

        Profesor profesor = (Profesor) persona;


        String sql = "UPDATE Persona SET Nombres = ?, Apellidos = ?, Email = ?, TipoContrato = ? WHERE ID = ?";

        try (var ps = conexion.prepareStatement(sql);) {

            ps.setString(1, profesor.getNombres());
            ps.setString(2, profesor.getApellidos());
            ps.setString(3, profesor.getEmail());
            ps.setString(4, profesor.getTipoContrato());
            ps.setDouble(5, profesor.getId());

            ps.executeUpdate();
            System.out.println("Profesor actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminar(Persona persona) {
        String sql = "DELETE FROM Persona WHERE id = ?";

        try (var ps = conexion.prepareStatement(sql);) {

            ps.setDouble(1, persona.getId());
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Profesor con ID " + persona.getId() + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró un profesor con ID " + persona.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
