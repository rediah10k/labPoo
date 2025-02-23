package com.educationallab.console.classes;

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
        int personaPos=traerPersonaPosicion(c);
        listado.remove(personaPos);
        guardarInformacion();
    }

    public void actualizar(CursoProfesor c) {
        int personaPos=traerPersonaPosicion(c);
        listado.set(personaPos, c);
        guardarInformacion();
    }

    public int traerPersonaPosicion(CursoProfesor c) {
        for (int i = 0; i < listado.size(); i++) {
            if (listado.get(i).getCurso().getId().equals(c.getCurso().getId())&&
                    listado.get(i).getProfesor().getId().equals(c.getProfesor().getId())) {
                return i;
            }
        }
        return -1;
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
        int i=1;
        for (CursoProfesor cursoProfesor : listado) {
            resultado.add(i+") "+cursoProfesor.toString());
            i++;
        }
        return resultado;
    }

    @Override
    public String toString() {
        return String.join("\n", imprimirListado());
    }
}
