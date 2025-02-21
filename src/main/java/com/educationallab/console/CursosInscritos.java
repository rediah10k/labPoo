package com.educationallab.console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Serializable, Servicios {
    private List<Inscripcion> listado;
    private static final String ARCHIVO = "inscripcionesCursos.dat";

    public CursosInscritos() {
        listado = new ArrayList<>();
        cargarDatos(); // Cargar datos previos al iniciar
    }

    public void inscribirCurso(Inscripcion inscripcion) {
        listado.add(inscripcion);
        guardarInformacion();
    }

    public void eliminar(Inscripcion inscripcion) {
        listado.remove(inscripcion);
        guardarInformacion();
    }

    public void actualizar(Inscripcion p) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getCurso().equals(p.getCurso()) &&
                listado.get(i).getEstudiante().equals(p.getEstudiante())) {
                listado.set(i, p);
                guardarInformacion();
                return;
            }
        }
    }

    public void guardarInformacion() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listado);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
                Object obj = in.readObject();
                if (obj instanceof List<?>) {
                    listado = (List<Inscripcion>) obj;
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
        for (Inscripcion inscripcion : listado) {
            resultado.add(inscripcion.toString());
        }
        return resultado;
    }

    @Override
    public String toString() {
        return String.join("\n", imprimirListado());
    }
}
