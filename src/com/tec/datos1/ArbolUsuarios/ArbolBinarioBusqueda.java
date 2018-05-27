package com.tec.datos1.ArbolUsuarios;
import com.tec.datos1.XMLUsuario.Usuario;

public class ArbolBinarioBusqueda {
    /* Atributos */
    public Usuario raiz;

    /* Contructories */
    public ArbolBinarioBusqueda(String id) {
        this.raiz = new Usuario(id);
    }

    public ArbolBinarioBusqueda(Usuario raiz) {
        this.raiz = raiz;
    }

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    /* Setters y Getters */
    public Usuario getRaiz() {
        return raiz;
    }

    public void setRaiz(Usuario raiz) {
        this.raiz = raiz;
    }

    private void addNodo2(Usuario nodo, Usuario raiz) {

        if (raiz == null) {
            this.setRaiz(nodo);
        } else {
            if (nodo.getId().equals(raiz.getId())) {
                //Cambiar por una ventana diciendo que esta repetido y debe cambiarse a uno valido
                System.out.println("Ya esta en el arbol");
                System.out.println(nodo.getId());

            }
            if (nodo.getId().compareTo(raiz.getId()) < 0) {
                //System.out.println("izq" + nodo.ID);

                if (raiz.getHojaIzquierda() == null) {
                    raiz.setHojaIzquierda(nodo);
                } else {
                    addNodo2(nodo, raiz.getHojaIzquierda());
                }
            } else if (nodo.getId().compareTo(raiz.getId()) > 0) {
                // System.out.println("der" + nodo.ID);
                if (raiz.getHojaDerecha() == null) {
                    raiz.setHojaDerecha(nodo);
                } else {
                    addNodo2(nodo, raiz.getHojaDerecha());
                }
            }
        }
    }

    public void addNodo(Usuario usuario) {
        this.addNodo2(usuario, this.raiz);
    }

    public void inOrden(Usuario usuario) {
        if (usuario != null) {
            inOrden(usuario.getHojaIzquierda());
            System.out.print(usuario.getId() + ",");
            inOrden(usuario.getHojaDerecha());
        }
    }




}
