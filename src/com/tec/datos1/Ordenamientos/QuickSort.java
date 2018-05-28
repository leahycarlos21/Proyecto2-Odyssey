package com.tec.datos1.Ordenamientos;

import com.tec.datos1.XMLCancion.Canciones;

public class QuickSort {

    public  void QuickSort(Canciones vec[]){
        final int N=vec.length;
        quickSort(vec, 0, N-1);
    }

    public void quickSort(Canciones vec[], int inicio, int fin){
        if(inicio>=fin) return;
        int pivote=vec[inicio].nombreCancion.toLowerCase().charAt(0);
        int elemIzq=inicio+1;
        int elemDer=fin;

        while(elemIzq<=elemDer){

            while(elemIzq<=fin && vec[elemIzq].nombreCancion.toLowerCase().charAt(0)<pivote){

                elemIzq++;
            }

            while(elemDer>inicio && vec[elemDer].nombreCancion.toLowerCase().charAt(0)>=pivote){
                elemDer--;
            }
            if(elemIzq<elemDer){
                Canciones temp=vec[elemIzq];
                vec[elemIzq]=vec[elemDer];
                vec[elemDer]=temp;
            }
        }

        if(elemDer>inicio){
            Canciones temp=vec[inicio];
            vec[inicio]=vec[elemDer];
            vec[elemDer]=temp;
        }
        quickSort(vec, inicio, elemDer-1);
        quickSort(vec, elemDer+1, fin);
    }
}
