package com.educationallab.console;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CursosInscritos {
private List<Inscripcion> listado;
private static final String ARCHIVO = "inscripcionesCursos.dat";

    public void inscribirCursos(Inscripcion inscripcion){
        listado.add(inscripcion);
        guardarInformacion();
    }

    public void eliminar(Inscripcion inscripcion){
        listado.remove(inscripcion);
        guardarInformacion();
    }

    public void actualizar(Inscripcion p) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getID().equals(p.getID())) {
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
                Object obj=in.readObject();
                if (obj instanceof List<?>) {
                    listado = (List<Inscripcion>) obj;
                } else {
                    System.out.println("Error: el archivo no contiene una lista válida.");
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        }
    }

}
