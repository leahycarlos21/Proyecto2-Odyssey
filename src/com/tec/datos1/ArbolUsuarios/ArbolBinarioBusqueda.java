package com.tec.datos1.ArbolUsuarios;

import com.tec.datos1.XMLUsuario.Usuario;

public class ArbolBinarioBusqueda {
    /* Atributos */
    public Usuario raiz;
    public Usuario[] arrayUsuario = new Usuario[auxCantidadArbolBB(getRaiz())];
    public int posicionUsuario = 0;

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
            if (nodo.getNickName().equals(raiz.getNickName())) {
            }
            if (nodo.getNickName().compareTo(raiz.getNickName()) < 0) {

                if (raiz.getHojaIzquierda() == null) {
                    raiz.setHojaIzquierda(nodo);
                } else {
                    addNodo2(nodo, raiz.getHojaIzquierda());
                }
            } else if (nodo.getNickName().compareTo(raiz.getNickName()) > 0) {
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
            System.out.print(usuario.getNickName() + ",");
            inOrden(usuario.getHojaDerecha());
        }
    }

    public int auxCantidadArbolBB(Usuario usuario) {
        return cantidadBB(usuario)/2;
    }
    public int cantidadBB(Usuario usuario) {
        if(usuario == null) {
            return 0;

        }else {
            int hijoDer=1+ cantidadBB(usuario.getHojaDerecha());
            int hijoIzq=1+cantidadBB(usuario.getHojaIzquierda());
            return hijoDer+hijoIzq ;
        }
    }

    public Usuario[] arbolToArrayAux(Usuario usuario) {
        arrayUsuario = new Usuario[auxCantidadArbolBB(getRaiz())];
        return arbolToArray(usuario);
    }
    public Usuario[] arbolToArray(Usuario usuario) {

        if (usuario != null) {
            arrayUsuario[posicionUsuario] = usuario;
            posicionUsuario++;
            arbolToArray(usuario.getHojaIzquierda());
            arbolToArray(usuario.getHojaDerecha());
        }
        return arrayUsuario;

    }






}

