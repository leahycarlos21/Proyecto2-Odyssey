package com.tec.datos1.XMLCancion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonIgnoreProperties(value={ "item" }, allowGetters=true)
public class Canciones {
    public String artista;
    public String album;
    public String nombreCancion;
    public String genero;
    public String letra;
    public byte[] bytesSong;
    public int ID;

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



    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    public byte[] getBytesSong() {
        return bytesSong;
    }

    public void setBytesSong(byte[] bytesSong) {
        this.bytesSong = bytesSong;
    }
}
