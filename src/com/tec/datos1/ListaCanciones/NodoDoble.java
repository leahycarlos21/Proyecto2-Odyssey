package com.tec.datos1.ListaCanciones;

import com.tec.datos1.XMLCancion.Canciones;

public class NodoDoble {
    private Canciones dato;
    public NodoDoble anterior, siguiente;

    public NodoDoble (Canciones dato){
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    public Canciones getDato() {
        return dato;
    }

    public void setDato(Canciones dato) {
        this.dato = dato;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
}
