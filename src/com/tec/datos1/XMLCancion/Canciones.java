package com.tec.datos1.XMLCancion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value={ "nil" }, allowGetters=true)
public class Canciones {
    public String artista;
    public String album;
    public String nombreCancion;
    public String genero;
    public String letra;
    public byte[] bytesSong;

    public Canciones(){

    }

    public Canciones(String artista, String album, String nombreCancion, String genero, String letra, byte[] byteSong) {
        this.artista = artista;
        this.album = album;
        this.nombreCancion = nombreCancion;
        this.genero = genero;
        this.letra = letra;
        this.bytesSong = byteSong;
    }

}
