package com.tec.datos1.Ordenamientos;

import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.ListaCanciones.NodoDoble;
import com.tec.datos1.XMLCancion.Canciones;

public class BubbleSort {


    private NodoDoble first;
    private NodoDoble last;

    public BubbleSort() {

    }

    public ListaDoble bubbleSort(ListaDoble listaDoble) throws Exception {
        int lengthlist=listaDoble.Size();
        first =listaDoble.getInPosition(0);
        last= listaDoble.getInPosition(lengthlist-1);

        Canciones aux;
        for(int i=0;i<lengthlist;i++){
            for(int j=0;j<lengthlist-1;j++){

                String name = listaDoble.getInPosition(j).getDato().album;
                String name1 = listaDoble.getInPosition(j+1).getDato().album;
                String cambioAminuscula = name.toLowerCase();
                String cambioAminuscula1= name1.toLowerCase();
                char characterValue = cambioAminuscula.charAt(0);
                char characterValue1 = cambioAminuscula1.charAt(0);
                int asciiValue = (int) characterValue;
                int asciiValue1 = (int) characterValue1;
                if(asciiValue1 < asciiValue){

                    aux=listaDoble.getInPosition(j+1).getDato();
                    listaDoble.getInPosition(j+1).setDato(listaDoble.getInPosition(j).getDato());
                    listaDoble.getInPosition(j).setDato(aux);

                }

            }
        }
        return listaDoble;
    }
    public void ImprimeLista(ListaDoble doble) throws Exception {
        int lengthlist=doble.Size();
        NodoDoble aux=doble.getInPosition(0);
        for(int i=0;i<lengthlist;i++){
            System.out.println("EL orden "+ doble.getInPosition(i).getDato().album);
        }
        System.out.println("largo "+doble.Size());
    }


}
