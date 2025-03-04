package com.educationallab.console.dao;
import com.educationallab.console.model.*;
import com.educationallab.console.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {


    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE TipoContrato IS NULL";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Programa programa = obtenerPrograma(rs.getDouble("ProgramaID"), con);
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

    public List<Profesor> listarDocentes() {
        List<Profesor> docentes = new ArrayList<>();
        String sql = "SELECT * FROM Persona WHERE TipoContrato IS NOT NULL";

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
                docentes.add(profesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.addAll(listarEstudiantes());
        personas.addAll(listarDocentes());
        return personas;
    }








    private Programa obtenerPrograma(Double programaId, Connection con) {
        Programa programa = null;
        String sql = "SELECT * FROM Programa WHERE ID = ?";
        FacultadDAO facultadDAO = new FacultadDAO();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDouble(1, programaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Facultad facultad = facultadDAO.obtenerFacultad(rs.getDouble("FacultadID"), con);
                    programa = new Programa(
                            rs.getDouble("ID"),
                            rs.getString("Nombre"),
                            rs.getInt("Duracion"),
                            rs.getDate("Registro").toLocalDate(),
                            facultad
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }



    public void insertarDatosSemilla() {
        String sql = "INSERT INTO Persona ( Nombres, Apellidos, Email,Tipo, TipoContrato) VALUES " +
                "('Juan', 'Pérez', 'juan.perez@universidad.edu','Profesor', 'Titular')";

        try (Connection con = ConexionBD.conectar();
             Statement stmt = con.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Datos semilla insertados en Persona (Decano).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertar(Estudiante estudiante) {
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



    public static void insertar(Profesor profesor) {
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

    public Persona buscarPersonaPorId(Double id) {


        Persona persona = null;
        String sql = "SELECT * FROM Persona WHERE ID = ?";
        ProgramaDAO programaDAO = new ProgramaDAO();


        try (Connection con = ConexionBD.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDouble(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Determinar el tipo de persona: Estudiante o Profesor
                    String tipo = rs.getString("tipo");
                    if ("ESTUDIANTE".equalsIgnoreCase(tipo)) {
                        Programa programa = programaDAO.obtenerProgramaPorId(rs.getDouble("ProgramaId"));
                        return new Estudiante(
                                rs.getDouble("ID"),
                                rs.getString("Nombres"),
                                rs.getString("Apellidos"),
                                rs.getString("Email"),
                                rs.getDouble("Codigo"),
                                programa,
                                rs.getBoolean("Activo"),
                                rs.getDouble("Promedio")

                        );
                    } else if ("PROFESOR".equalsIgnoreCase(tipo)) {
                        return new Profesor(
                                rs.getDouble("ID"),
                                rs.getString("Nombres"),
                                rs.getString("Apellidos"),
                                rs.getString("Email"),
                                rs.getString("TipoContrato")
                        );
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actualizar(Persona persona) {
        String sql;
        if (persona instanceof Estudiante) {
            sql = "UPDATE Persona SET Nombres = ?, Apellidos = ?, Email = ?, Codigo = ?, ProgramaID = ?, Activo = ?, Promedio = ? WHERE ID = ?";
        } else {
            sql = "UPDATE Persona SET Nombres = ?, Apellidos = ?, Email = ?, TipoContrato = ? WHERE ID = ?";
        }

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, persona.getNombres());
            ps.setString(2, persona.getApellidos());
            ps.setString(3, persona.getEmail());

            if (persona instanceof Estudiante estudiante) {
                ps.setDouble(4, estudiante.getCodigo());
                ps.setDouble(5, estudiante.getPrograma().getId());
                ps.setBoolean(6, estudiante.isActivo());
                ps.setDouble(7, estudiante.getPromedio());
                ps.setDouble(8, estudiante.getId());
            } else if (persona instanceof Profesor profesor) {
                ps.setString(4, profesor.getTipoContrato());
                ps.setDouble(5, profesor.getId());
            }

            ps.executeUpdate();
            System.out.println("Persona actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Double id) {
        String sql = "DELETE FROM Persona WHERE ID = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, id);
            ps.executeUpdate();
            System.out.println("Persona eliminada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










}
