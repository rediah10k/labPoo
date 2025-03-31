package com.educationallab.console.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private  final String URL = "jdbc:h2:tcp://localhost/~/personasDB;DB_CLOSE_DELAY=-1";
    private  final String USER = "admin";
    private  final String PASSWORD = "tecno";
    private Connection conexion;
    private static ConexionBD instancia;

    static {
        inicializarBD();
    }

    private ConexionBD(){
        try {
            this.conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos");
        }

    }

    public static ConexionBD getInstancia() {
        if (instancia == null) {
            synchronized (ConexionBD.class) {
                if (instancia == null) {
                    instancia = new ConexionBD();
                }
            }
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return conexion;
    }



    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                instancia = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void inicializarBD() {
        try (Connection con = getInstancia().getConexion();
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
                    ID DOUBLE PRIMARY KEY AUTO_INCREMENT,
                    ProfesorID DOUBLE NOT NULL,
                    CursoID INTEGER NOT NULL,
                    Año INTEGER NOT NULL,
                    Semestre INTEGER NOT NULL,
                    FOREIGN KEY (ProfesorID) REFERENCES Persona(ID) ON DELETE CASCADE,
                    FOREIGN KEY (CursoID) REFERENCES Curso(ID) ON DELETE CASCADE
                );
                
                -- 6️⃣ Crear Inscripción (requiere Curso y Persona)
                CREATE TABLE Inscripcion (
                    ID DOUBLE PRIMARY KEY AUTO_INCREMENT,
                    CursoID INTEGER NOT NULL,
                    Año INTEGER NOT NULL,
                    Semestre INTEGER NOT NULL,
                    EstudianteID DOUBLE NOT NULL,
                    FOREIGN KEY (CursoID) REFERENCES Curso(ID) ON DELETE CASCADE,
                    FOREIGN KEY (EstudianteID) REFERENCES Persona(ID) ON DELETE CASCADE
                );
            INSERT INTO Persona ( Nombres, Apellidos, Email,Tipo, TipoContrato) VALUES 
            ('Juan', 'Pérez', 'juan.perez@universidad.edu','Profesor', 'Titular');
            INSERT INTO Facultad ( Nombre, DecanoID) VALUES 
            ('Facultad de Ingeniería', 1.0), 
            ('Facultad de Ciencias', 1.0),
            ('Facultad de Humanidades', 1.0);       
            INSERT INTO Programa (Nombre, Duracion, Registro, FacultadID) VALUES 
           ( 'Ingeniería de Sistemas', 5, '2023-01-01', 1), 
           ('Matemáticas', 4, '2022-05-15', 2),  
           ('Historia', 4, '2021-08-10', 3);
           INSERT INTO Curso ( Nombre, ProgramaID, Activo) VALUES
           ('Matemáticas Básicas', 2, true),
           ('Estructuras de Datos', 1, true),
           ( 'Historia Contemporánea', 3, true)
            """;

            stmt.executeUpdate(sql);
            System.out.println("Base de datos inicializada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
