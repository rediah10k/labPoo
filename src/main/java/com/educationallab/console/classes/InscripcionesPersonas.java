package com.educationallab.console.classes;

import java.io.*;
import java.util.ArrayList;

public class InscripcionesPersonas {
    private ArrayList<Persona> listado = new ArrayList<>();
    private static final String ARCHIVO = "inscripcionesPersonas.dat";

    public void inscribir(Persona persona) {
        listado.add(persona);
        guardarInformacion();
    }

    public void eliminar(Persona persona) {
        listado.remove(persona);
        guardarInformacion();
    }

    public void actualizar(Persona persona) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getId().equals(persona.getId())) {
                listado.set(i, persona);
                guardarInformacion();
                return;
            }
        }
        System.out.println("⚠ No se encontró la persona con ID: " + persona.getId());
    }

    public void guardarInformacion() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listado);
            System.out.println("✅ Archivo guardado con éxito. Personas: " + listado.size());
        } catch (IOException e) {
            System.out.println("❌ Error al guardar personas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")    
    public void cargarDatos() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            System.out.println("⚠ Archivo de personas no encontrado. Se creará uno nuevo.");
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            listado = (ArrayList<Persona>) in.readObject();
            System.out.println("Personas cargadas. Total: " + listado.size());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar personas: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return listado.toString();
    }
}

