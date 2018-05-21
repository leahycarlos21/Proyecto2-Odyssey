package com.tec.datos1.ListaCanciones;

import com.tec.datos1.XMLCancion.Canciones;

public class ListaDoble {

    private NodoDoble raiz;

    public ListaDoble() {
        raiz = null;
    }

    /**
     * Retorna la cantiadd de dos
     */
    public int cantidad() {
        int cant = 0;
        NodoDoble auxNodo = raiz;
        while (auxNodo != null) {
            auxNodo = auxNodo.getSiguiente();
            cant++;
        }
        return cant;
    }

    public void insertar(int pos, Canciones dato) {
        if (pos <= cantidad() + 1) {
            NodoDoble nuevo = new NodoDoble(dato);
            if (pos == 1) {
                nuevo.siguiente = raiz;
                if (raiz != null)
                    raiz.anterior = nuevo;

                raiz = nuevo;
            } else if (pos == cantidad() + 1) {
                NodoDoble auxNodo = raiz;
                while (auxNodo.siguiente != null) {
                    auxNodo = auxNodo.siguiente;
                }
                auxNodo.siguiente = nuevo;
                nuevo.anterior = auxNodo;
                nuevo.siguiente = null;
            } else {
                NodoDoble auxNodo = raiz;
                for (int i = 1; i <= pos - 2; i++)
                    auxNodo = auxNodo.siguiente;
                NodoDoble siguiente = auxNodo.siguiente;
                auxNodo.siguiente = nuevo;
                nuevo.anterior = auxNodo;
                nuevo.siguiente = siguiente;
                siguiente.anterior = nuevo;
            }

        }
    }

    public void eliminar(int pos) {
        if (pos <= cantidad()) {
            if (pos == 1) {
                raiz = raiz.siguiente;
                if (raiz != null)
                    raiz.anterior = null;
            } else {
                NodoDoble auxNodo = raiz;

                for (int i = 1; i <= pos - 2; i++)
                    auxNodo = auxNodo.siguiente;
                NodoDoble prox = auxNodo.siguiente;
                prox = prox.siguiente;
                auxNodo.siguiente = prox;
                if (prox != null)
                    prox.anterior = auxNodo;
            }
        }
    }

    public Canciones obtenerDato(int pos) {
        if (pos <= cantidad()) {
            NodoDoble auxNodo = raiz;
            for (int i = 1; i < pos; i++)
                auxNodo = auxNodo.siguiente;

            return auxNodo.getDato();
        }
        return null;
    }
    public int eliminarDato(String artista,String cancion){
        NodoDoble auxNodo = raiz;
        int num=1;
        while(auxNodo != null){
            if (auxNodo.getDato().artista.equals(artista) && auxNodo.getDato().nombreCancion.equals(cancion)){
                eliminar(num);
                return num;
            }
            auxNodo = auxNodo.siguiente;
            num++;
        }
        System.out.println("Retorno nullllllllllllll");
        return 0;
    }
    public Canciones obtenerDato(String artista,String cancion){
        NodoDoble auxNodo = raiz;
        while(auxNodo != null){
            if (auxNodo.getDato().artista.equals(artista) && auxNodo.getDato().nombreCancion.equals(cancion)){
                return auxNodo.getDato();
            }
            auxNodo = auxNodo.siguiente;
        }
        System.out.println("Retorno nullllllllllllll");
        return null;
    }

    public void imprimir() {
        NodoDoble auxNodo = raiz;
        int num = 1;
        while (auxNodo != null) {
            System.out.println(num + " -> " + auxNodo.getDato().nombreCancion);
            num++;
            auxNodo = auxNodo.siguiente;
        }
    }

    public boolean existe(Canciones cancion) {
        NodoDoble auxNodo = raiz;
        while (auxNodo != null) {
            if (auxNodo.getDato().nombreCancion.equals(cancion.nombreCancion) && auxNodo.getDato().artista.equals(cancion.artista))
                return true;
            auxNodo = auxNodo.siguiente;
        }
        return false;
    }


}
