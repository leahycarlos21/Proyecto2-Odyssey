package com.tec.datos1.ListaCanciones;

import com.tec.datos1.XMLCancion.Canciones;

public class ListaDoble {

    private NodoDoble raiz;
    int length;

    private NodoDoble first, last;


    public ListaDoble() {
        raiz = null;
    }

    /**
     * Retorna la cantidad de dos
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

    public int eliminarDato(String artista, String cancion) {
        NodoDoble auxNodo = raiz;
        int num = 1;
        while (auxNodo != null) {
            if (auxNodo.getDato().artista.equals(artista) && auxNodo.getDato().nombreCancion.equals(cancion)) {
                eliminar(num);
                return num;
            }
            auxNodo = auxNodo.siguiente;
            num++;
        }
        System.out.println("Retorno nullllllllllllll");
        return 0;
    }

    public int obtenerPos(String artista, String cancion) {
        NodoDoble auxNodo = first;
        int num = 0;
        while (auxNodo != null) {
            if (auxNodo.getDato().artista.equals(artista) && auxNodo.getDato().nombreCancion.equals(cancion)) {
                return num;
            }
            num++;
            auxNodo = auxNodo.siguiente;
        }
        System.out.println("Retorno nullllllllllllll");
        return -1;
    }

    public void remove(int index) {
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if (index >= 0 && index < length) {
            // Consulta si el nodo a eliminar es el primero
            if (index == 0) {

                // Elimina el primer nodo apuntando al siguinte.
                first = first.getSiguiente();
                last = first.getAnterior().getAnterior();
            } // En caso que el nodo a eliminar este por el medio
            // o sea el ultimo
            else {
                // Crea una copia de la lista.
                NodoDoble aux = first;

                // Recorre la lista hasta llegar al nodo anterior al eliminar.
                for (int i = 0; i < index - 1; i++) {
                    aux = aux.getSiguiente();

                }
                // Guarda el nodo siguiente al nodo a eliminar.
                NodoDoble next = aux.getSiguiente();
                NodoDoble previous = aux;
                // Elimina la referencia del nodo apuntando al nodo siguiente.

                aux.setSiguiente(next.getSiguiente());
                aux.setAnterior(previous.getAnterior());

            }
            // Disminuye el contador de tamaño de la lista.
            length--;
        }
    }

    public NodoDoble obtenerDato(String artista, String cancion) {
        NodoDoble auxNodo = first;
        while (auxNodo != null) {
            if (auxNodo.getDato().artista.equals(artista) && auxNodo.getDato().nombreCancion.equals(cancion)) {
                return auxNodo;
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

    public void add(Canciones enemy) {
        // Define un nuevo nodo.
        NodoDoble New = new NodoDoble(enemy);
        // Agrega al valor al nodo.
        New.setDato(enemy);
        // Consulta si la lista esta vacia.
        if (isEmpty()) {
            // Inicializa la lista agregando como inicio al nuevo nodo.
            last = New;
            first = last;
            // Caso contrario va agregando los nodos al inicio de la lista.
        } else {
            // Crea una copia de la lista.
            NodoDoble aux = first;
            // Recorre la lista hasta llegar al ultimo nodo.
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            // Agrega el nuevo nodo al final de la lista.
            aux.setSiguiente(New);
            New.setAnterior(aux);
        }
        // Incrementa el contador de tamaño de la lista.
        length++;
    }

    public boolean isEmpty() {
        return first == null;
    }


    public int Size() {
        return length;
    }

    /**
     * Obtiene el nodo en x posición de la lista.
     * Método getInPosition
     *
     * @param index
     * @return Un nodo.
     * @throws Exception
     */
    public NodoDoble getInPosition(int index) throws Exception {
        // Consulta si el valor existe en la lista.
        if (index < this.length) {
            // Crea una copia de la lista.
            NodoDoble aux = first;
            // COntado para almacenar la posición del nodo.

            // Recoore la lista hasta llegar al nodo de referencia.
            while (index > 0) {//&& aux != null ){

                aux = aux.getSiguiente();
                index -= 1;
            }
            return aux;
        } else {
            throw new Exception("Valor inexistente en la lista.");

        }

    }

}