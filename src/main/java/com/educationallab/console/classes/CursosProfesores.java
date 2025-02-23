package com.educationallab.console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Serializable, Servicios {
    private ArrayList<CursoProfesor> listado;
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "cursos_profesores.dat";

    public CursosProfesores() {
        listado = new ArrayList<>();
        cargarDatos();
    }

    public void inscribir(CursoProfesor c) {
        listado.add(c);
        guardarInformacion();
    }

    public void eliminar(CursoProfesor c) {
        listado.remove(c);
        guardarInformacion();
    }

    public void actualizar(CursoProfesor c) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getProfesor().equals(c.getProfesor()) &&
                listado.get(i).getCurso().equals(c.getCurso())) {
                listado.set(i, c);
                guardarInformacion();
                return;
            }
        }
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
        File archivo = new File(FILE_NAME);
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                Object obj = in.readObject();
                if (obj instanceof ArrayList<?>) {
                    listado = (ArrayList<CursoProfesor>) obj;
                } else {
                    System.out.println("Error: el archivo no contiene una lista válida.");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        } else {
            listado = new ArrayList<>();
        }
    }

    @Override
    public String imprimirPosicion(Integer posicion) {
        if (posicion >= 0 && posicion < listado.size()) {
            return listado.get(posicion).toString();
        }
        return "Posición fuera de rango";
    }

    @Override
    public Integer cantidadActual() {
        return listado.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> resultado = new ArrayList<>();
        for (CursoProfesor cursoProfesor : listado) {
            resultado.add(cursoProfesor.toString());
        }
        return resultado;
    }

    @Override
    public String toString() {
        return String.join("\n", imprimirListado());
    }
}
