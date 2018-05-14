package com.tec.datos1.FuncionesServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.XMLCancion.Canciones;

import java.io.File;
import java.io.IOException;

public class CancionAlmacenaje {
    ListaDoble listaCanciones = new ListaDoble();
    File archivo = new File("Canciones.json");
    ObjectMapper objectmapper = new ObjectMapper();
    Canciones[] canciones;

    public CancionAlmacenaje() throws IOException {

        try {
            sincronizarJsonToLista();
            listaCanciones.imprimir();
        }catch(Exception e){

        }

    }

    /**
     * Añade el array de canciones entrante a la lista
     */
    public void addCancionesEntrantes(Canciones[] cancionesEntrantes) throws IOException {
        int num = 0;
        while (num < cancionesEntrantes.length) {
            System.out.println("Entro " + cancionesEntrantes[num].nombreCancion);
            if (listaCanciones.existe(cancionesEntrantes[num]) == false) {
                listaCanciones.insertar(1, cancionesEntrantes[num]);
            }
            sincronizarListaToJson();
            num++;
        }

        listaCanciones.imprimir();

    }

    private void sincronizarJsonToLista() throws IOException {
        Canciones[] canciones = objectmapper.readValue(archivo, Canciones[].class);
        for (int i = 0; i < canciones.length; i++) {
            listaCanciones.insertar(1, canciones[i]);
        }
    }

    private void sincronizarListaToJson() throws IOException {
        Canciones[] cancionesLista = new Canciones[listaCanciones.cantidad()];
        int num = 0;
        while (num < cancionesLista.length) {
            cancionesLista[num] = listaCanciones.obtenerDato(num + 1);
            num++;
        }
        objectmapper.writerWithDefaultPrettyPrinter().writeValue(archivo, cancionesLista);

    }

    public boolean almacenarCacion() {
        boolean almacenadoExito = true;
/**
 * Que al entrar lo convierta en Json y agregar una lista
 */


        return almacenadoExito;
    }
}
