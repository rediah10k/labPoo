package com.educationallab.console;

import java.io.*;
import java.util.ArrayList;

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
        if (listado.remove(p)) {
            guardarInformacion();
        }
    }

    public void actualizar(Persona p) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getId().equals(p.getId())) {
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

    // Nuevo método agregado
    public void imprimirListado() {
        System.out.println("Lista de personas inscritas:");
        for (Persona p : listado) {
            System.out.println(p);
        }
    }
}
