package com.educationallab.console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesPersonas implements Serializable {
    private ArrayList<Persona> listado;
    private static final long serialVersionUID = 1L;
    private static final String ARCHIVO = "inscripcionesPersonas.dat";

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
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
                listado = (ArrayList<Persona>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        }
    }
}
