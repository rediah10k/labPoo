package com.educationallab.console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos {
private List<Inscripcion> listado;
private static final String ARCHIVO = "inscripcionesCursos.dat";

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
                listado = (List<Inscripcion>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        }
    }

}
