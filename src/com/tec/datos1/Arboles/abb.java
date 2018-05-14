package com.tec.datos1.Arboles;


public class abb {
    private class nodoArbol {
        private abb hd;
        private abb hi;
        private int dato;

        private void nodoArbol() {
            hd = null;
            hi = null;
            dato = 0;
        }
    }

    public nodoArbol raiz;

    public void abb(){
        nodoArbol raiz = new nodoArbol();
    }

    public boolean esVacio(){
        return (raiz == null);
    }


    public void insertar(String a){
        char characterValue = a.charAt(0);
        int asciiValue = (int) characterValue;
        if (esVacio()) {
            nodoArbol nuevo = new nodoArbol();
            nuevo.dato = asciiValue;
            nuevo.hd = new abb();
            nuevo.hi = new abb();
            raiz = nuevo;
        }
        else {
            if (asciiValue> raiz.dato) {
                (raiz.hd).insertar(a);
            }
            if (asciiValue < raiz.dato){
                (raiz.hi).insertar(a);
            }
        }
    }

    public boolean existe(String a){
        int asciiValue = 0;
        for (int x=0; x<a.length(); x++){
            asciiValue = (int) a.codePointAt(x);

        }
        if (!esVacio()) {

            if (asciiValue == raiz.dato) {
                return true;
            }
            else {


                if (asciiValue < raiz.dato) {
                    raiz.hi.existe(a);
                }
                else {
                    raiz.hd.existe(a);
                }
            }
        }
        return false;


    }

}
