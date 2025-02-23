package com.educationallab.console.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesPersonas implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ARCHIVO = "inscripcionesPersonas.dat";

    private final ArrayList<Persona> listado;

    public InscripcionesPersonas() {
        listado = new ArrayList<>();
        cargarDatos();
    }

    public void inscribir(Persona p) {
        listado.add(p);
        guardarInformacion();
    }

    public void eliminar(Persona p) {
        int personaPos=traerPersonaPosicion(p);
        listado.remove(personaPos);
        guardarInformacion();
    }

    public void actualizar(Persona p) {
        int personaPos=traerPersonaPosicion(p);
        listado.set(personaPos, p);
        guardarInformacion();
    }

    public int traerPersonaPosicion(Persona p) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getId().equals(p.getId())) {
                return i;
            }
        }
        return -1;
    }

    public void guardarInformacion() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(listado);
        } catch (IOException e) {
            System.out.println("Error al guardar información: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
                listado.clear();  // Asegurar que no haya duplicados
                listado.addAll((ArrayList<Persona>) in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        }
    }


    public List<String> imprimirListado() {
        java.util.List<String> resultado = new ArrayList<>();
        int i=1;
        for (Persona p : listado) {
            resultado.add(i+") "+p.toString());
            i++;
        }
        return resultado;

    }

    public String toString() {
        return String.join("\n", imprimirListado());
    }
}
