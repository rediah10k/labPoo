package com.educationallab.console.model;

import com.educationallab.console.services.Servicios;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosProfesores implements Servicios {
    private ArrayList<CursoProfesor> listado = new ArrayList<>();
    private static final String ARCHIVO = "cursosProfesores.dat";

    public void inscribir(CursoProfesor cursoProfesor) {
        listado.add(cursoProfesor);
        guardarInformacion();
    }

    public void guardarInformacion() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listado);
            System.out.println("✅ Archivo guardado con éxito. Cursos-profesores: " + listado.size());
        } catch (IOException e) {
            System.out.println("❌ Error al guardar cursos-profesores: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("⚠ Archivo de cursos-profesores no encontrado. Se creará uno nuevo.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            listado = (ArrayList<CursoProfesor>) in.readObject();
            System.out.println("✅ Cursos-profesores cargados. Total: " + listado.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error al cargar cursos-profesores: " + e.getMessage());
        }
    }

    @Override
    public String imprimirPosicion(Integer posicion) {
        if (posicion >= 0 && posicion < listado.size()) {
            return listado.get(posicion).toString();
        }
        return "⚠ Posición inválida.";
    }

    @Override
    public Integer cantidadActual() {
        return listado.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> lista = new ArrayList<>();
        for (CursoProfesor cp : listado) {
            lista.add(cp.toString());
        }
        return lista;
    }

    @Override
    public String toString() {
        return listado.toString();
    }
}

