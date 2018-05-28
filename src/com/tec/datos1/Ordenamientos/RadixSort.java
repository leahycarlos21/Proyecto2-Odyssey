package com.tec.datos1.Ordenamientos;

import com.tec.datos1.XMLCancion.Canciones;

public class RadixSort {
    public  void RadixSort(Canciones a[]) {

        Canciones m = a[0];
        int exp = 1;
        int n = a.length;
        Canciones[] b = new Canciones[a.length];
        for (int i = 1; i < n; i++)
            if (a[i].artista.toLowerCase().charAt(0) > m.artista.toLowerCase().charAt(0))
                m = a[i];
        while (m.artista.toLowerCase().charAt(0) / exp > 0) {
            int[] bucket = new int[10];

            for (int i = 0; i < n; i++)
                bucket[(a[i].artista.toLowerCase().charAt(0) / exp) % 10]++;
            for (int i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (int i = n - 1; i >= 0; i--)
                b[--bucket[(a[i].artista.toLowerCase().charAt(0) / exp) % 10]] = a[i];
            for (int i = 0; i < n; i++)
                a[i] = b[i];
            exp *= 10;
        }
    }


    public  void imprimirVector(Canciones vec[]) {
        for (int i = 0; i < vec.length; i++) {
            System.out.print(vec[i].artista + " ");
        }
    }


}