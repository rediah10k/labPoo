package com.educationallab.console.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos implements Servicios {
    private ArrayList<Inscripcion> listado = new ArrayList<>();
    private static final String ARCHIVO = "inscripcionesCursos.dat";

    public void inscribirCurso(Inscripcion inscripcion) {
        listado.add(inscripcion);
        guardarInformacion();
    }

    public void eliminar(Inscripcion inscripcion) {
        listado.remove(inscripcion);
        guardarInformacion();
    }

    public void actualizar(Inscripcion inscripcion) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getCurso().getId().equals(inscripcion.getCurso().getId())) {
                listado.set(i, inscripcion);
                guardarInformacion();
                return;
            }
        }
        System.out.println("⚠ No se encontró la inscripción con ID: " + inscripcion.getCurso().getId());
    }

    public void guardarInformacion() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listado);
            System.out.println("✅ Archivo guardado con éxito. Inscripciones: " + listado.size());
        } catch (IOException e) {
            System.out.println("❌ Error al guardar inscripciones: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("⚠ Archivo de inscripciones no encontrado. Se creará uno nuevo.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            listado = (ArrayList<Inscripcion>) in.readObject();
            System.out.println("✅ Inscripciones cargadas. Total: " + listado.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error al cargar inscripciones: " + e.getMessage());
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
        for (Inscripcion inscripcion : listado) {
            lista.add(inscripcion.toString());
        }
        return lista;
    }

    @Override
    public String toString() {
        return listado.toString();
    }
}


