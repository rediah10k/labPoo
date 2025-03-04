package com.educationallab.console.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static final String URL = "jdbc:h2:tcp://localhost/~/personasDB"; // Base de datos en archivo
    private static final String USER = "admin";
    private static final String PASSWORD = "tecno";

    static {
        inicializarBD();
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void inicializarBD() {
        try (Connection con = conectar();
             Statement stmt = con.createStatement()) {
            String sql = """
                DROP TABLE IF EXISTS CursoProfesor;
                DROP TABLE IF EXISTS Inscripcion;                
                DROP TABLE IF EXISTS Curso;
                DROP TABLE IF EXISTS Programa;
                DROP TABLE IF EXISTS Facultad;
                DROP TABLE IF EXISTS Persona;
                
                -- 1️⃣ Crear Persona primero
                CREATE TABLE Persona (
                    ID DOUBLE PRIMARY KEY AUTO_INCREMENT,
                    Nombres VARCHAR(100) NOT NULL,
                    Apellidos VARCHAR(100) NOT NULL,
                    Email VARCHAR(100) UNIQUE NOT NULL,
                    Tipo VARCHAR(20) NOT NULL CHECK (Tipo IN ('Estudiante', 'Profesor')),
                    -- Atributos exclusivos de Estudiante
                    Codigo DOUBLE UNIQUE,
                    ProgramaID DOUBLE,
                    Activo BOOLEAN,
                    Promedio DOUBLE,
                    -- Atributos exclusivos de Profesor
                    TipoContrato VARCHAR(50)
                );
                
                -- 2️⃣ Ahora que Persona existe, podemos crear Facultad
                CREATE TABLE Facultad (
                    ID DOUBLE PRIMARY KEY AUTO_INCREMENT,
                    Nombre VARCHAR(100) NOT NULL,
                    DecanoID DOUBLE,
                    FOREIGN KEY (DecanoID) REFERENCES Persona(ID) ON DELETE SET NULL
                );
                
                -- 3️⃣ Crear Programa (requiere Facultad)
                CREATE TABLE Programa (
                    ID DOUBLE PRIMARY KEY AUTO_INCREMENT,
                    Nombre VARCHAR(100) NOT NULL,
                    Duracion DOUBLE NOT NULL,
                    Registro DATE NOT NULL,
                    FacultadID DOUBLE NOT NULL,
                    FOREIGN KEY (FacultadID) REFERENCES Facultad(ID) ON DELETE CASCADE
                );
                
                -- 4️⃣ Crear Curso (requiere Programa)
                CREATE TABLE Curso (
                    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
                    Nombre VARCHAR(100) NOT NULL,
                    ProgramaID DOUBLE NOT NULL,
                    Activo BOOLEAN DEFAULT TRUE,
                    FOREIGN KEY (ProgramaID) REFERENCES Programa(ID) ON DELETE CASCADE
                );
                
                -- 5️⃣ Crear CursoProfesor (requiere Curso y Persona)
                CREATE TABLE CursoProfesor (
                    ProfesorID DOUBLE NOT NULL,
                    CursoID INTEGER NOT NULL,
                    Año INTEGER NOT NULL,
                    Semestre INTEGER NOT NULL,
                    PRIMARY KEY (ProfesorID, CursoID, Año, Semestre),
                    FOREIGN KEY (ProfesorID) REFERENCES Persona(ID) ON DELETE CASCADE,
                    FOREIGN KEY (CursoID) REFERENCES Curso(ID) ON DELETE CASCADE
                );
                
                -- 6️⃣ Crear Inscripción (requiere Curso y Persona)
                CREATE TABLE Inscripcion (
                    CursoID INTEGER NOT NULL,
                    Año INTEGER NOT NULL,
                    Semestre INTEGER NOT NULL,
                    EstudianteID DOUBLE NOT NULL,
                    PRIMARY KEY (CursoID, Año, Semestre, EstudianteID),
                    FOREIGN KEY (CursoID) REFERENCES Curso(ID) ON DELETE CASCADE,
                    FOREIGN KEY (EstudianteID) REFERENCES Persona(ID) ON DELETE CASCADE
                );
                
               
            """;
            stmt.executeUpdate(sql);
            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
