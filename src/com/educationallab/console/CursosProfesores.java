package com.educationallab.console;
import java.io.*;
import java.util.ArrayList;


public class CursosProfesores implements Serializable {
    private ArrayList<CursoProfesor> listado;
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "cursos_profesores.dat";

    public CursosProfesores() {
        listado = new ArrayList<>();
        cargarDatos();  // Carga los datos almacenados previamente
    }

    public void inscribir(CursoProfesor c) {
        listado.add(c);
        guardarInformacion();
    }

    public void eliminar(CursoProfesor c) {
        listado.remove(c);
        guardarInformacion();
    }

    public void guardarInformacion() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(listado);
        } catch (IOException e) {
            System.out.println("Error al guardar información: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            listado = (ArrayList<CursoProfesor>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo, iniciando con una lista vacía.");
            listado = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            listado = new ArrayList<>();
        }
    }

    public void imprimirListado() {
        for (CursoProfesor c : listado) {
            System.out.println(c.toString());
        }
    }

    public ArrayList<CursoProfesor> obtenerListado() {
        return listado;
    }
}
