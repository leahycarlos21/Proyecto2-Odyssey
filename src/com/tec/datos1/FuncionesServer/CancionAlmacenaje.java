package com.tec.datos1.FuncionesServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.XMLCancion.Canciones;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import java.io.File;
import java.io.IOException;

public class CancionAlmacenaje {
    private ListaDoble listaCanciones = new ListaDoble();
    private File archivo = new File("Canciones.json");
    private Canciones[] canciones;
    String apiKey = "f49c29ea71404d6b85b6b514d19df23e";
    MusixMatch musixMatch = new MusixMatch(apiKey);
    public CancionAlmacenaje() {

        try {
            sincronizarJsonToLista();
            listaCanciones.imprimir();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Canciones[] enviarCancionSolicitad(String artista, String cancion) {
        Canciones[] listaenviar = new Canciones[1];

        listaenviar[0] = listaCanciones.obtenerDato(artista, cancion);
        System.out.println("EnviarCanciones" + listaenviar[0].bytesSong);

        //System.out.print(listaenviar[0].bytesSong);
        //   System.out.print("ho----"+listaenviar[0].bytesSong);


        return listaenviar;
    }

    public void editarCancion(Canciones[] listEdit) throws IOException {
        eliminarCancion(listEdit[0]);

        Canciones[] auxArray = new Canciones[1];
        auxArray[0]=listEdit[1];
        addCancionesEntrantes(auxArray);

    }

    public void editarMusixMatch (Canciones[] cancionEntrante) throws MusixMatchException {
        String cancionNombre = cancionEntrante[0].nombreCancion;
        String artistNombre = cancionEntrante[0].artista;

        Track datosCancion = musixMatch.getMatchingTrack(cancionNombre,artistNombre);
        TrackData data = datosCancion.getTrack();
        System.out.println("AlbumID : "    + data.getAlbumId());
        System.out.println("Album Name : " + data.getAlbumName());
        System.out.println("Artist ID : "  + data.getArtistId());
        System.out.println("Artist Name : " + data.getArtistName());
        System.out.println("Track ID : "   + data.getTrackId());


        int CancionID = data.getTrackId();
        Lyrics letra = musixMatch.getLyrics(CancionID);
        System.out.println("Lyrics Body     : "     + letra.getLyricsBody());
        System.out.println("Lyrics Body     : " + letra.getLyricsLang());

    }

    public Canciones[] enviarTodaCanciones() {
        int num = 0;
        ListaDoble list = listaCanciones;
        Canciones[] cancionis = new Canciones[list.cantidad()];
        while (num < list.cantidad()) {

            cancionis[num] = list.obtenerDato(num + 1);
            //  cancionis[num].bytesSong = new byte[3];
            num++;
        }
//        System.out.println("pppppppp----"+listaCanciones.obtenerDato("CocoFunka","Chucaro").bytesSong);
        //      System.out.println("pppppp----"+listaCanciones.obtenerDato("CocoFunka","Chucaro").bytesSong);

        return cancionis;

    }

    public Canciones[] enviarTodaCanciones(int posicion) {

        ListaDoble list = listaCanciones;
        Canciones[] cancionis = new Canciones[3];
        int num = 0;
        int largo = posicion + 3;
        posicion = posicion - 1;
        int longis = canciones.length;

        if(posicion==longis){
            return null;
        }
        while (num < 3) {
            if (longis == 0) {

                return null;
            }

            if (posicion == canciones.length - 1) {
                cancionis[num] = canciones[posicion];

                return cancionis;
            }
            cancionis[num] = canciones[posicion];

            posicion++;
            num++;
        }

        return cancionis;

    }


    /**
     * Añade el array de canciones entrante a la lista
     */
    public void addCancionesEntrantes(Canciones[] cancionesEntrantes) throws IOException {
        int num = 0;
        while (num < cancionesEntrantes.length) {
            System.out.println("Entro " + cancionesEntrantes[num].nombreCancion);
            if (listaCanciones.existe(cancionesEntrantes[num]) == false) {
                listaCanciones.insertar(1, cancionesEntrantes[num]);
            }
            num++;
        }
        sincronizarListaToJson();


        // listaCanciones.imprimir();

    }
    private void sincronizarListaToArray(){
         canciones= new Canciones[listaCanciones.cantidad()];
        int num = 0;
        while (num < canciones.length) {
            canciones[num] = listaCanciones.obtenerDato(num + 1);
            num++;
        }
    }
    private void sincronizarJsonToLista() throws IOException {
        ObjectMapper objectmapper = new ObjectMapper();

        canciones = objectmapper.readValue(archivo, Canciones[].class);
        for (int i = 0; i < canciones.length; i++) {
            listaCanciones.insertar(i + 1, canciones[i]);
            System.out.println(canciones[i].nombreCancion);
            System.out.println(canciones[i].artista);
            System.out.println(canciones[i].bytesSong);

        }
        //     System.out.println("-----"+listaCanciones.obtenerDato(2).nombreCancion);
        //   System.out.println("-----"+listaCanciones.obtenerDato(2).artista);
        // System.out.println("----...."+listaCanciones.obtenerDato("Los Angeles Azules","COmo Te Voy A Olvidar").bytesSong);


        //      System.out.println(".....000000" + listaCanciones.obtenerDato(0).bytesSong);
        //    System.out.println(".....0000" + listaCanciones.obtenerDato("CocoFunka", "Chucaro").bytesSong);

    }

    private void sincronizarListaToJson() throws IOException {
        sincronizarListaToArray();
        ObjectMapper objectmapper = new ObjectMapper();

        Canciones[] cancionesaJson = new Canciones[listaCanciones.cantidad()];
        int num = 0;
        while (num < cancionesaJson.length) {
            cancionesaJson[num] = listaCanciones.obtenerDato(num + 1);
            num++;
        }
        objectmapper.writeValue(archivo, cancionesaJson);

    }

    public void eliminarCancion(Canciones cancion) throws IOException {
        listaCanciones.eliminarDato(cancion.artista, cancion.nombreCancion);
        sincronizarListaToJson();
    }
    public void eliminarCancion(Canciones[] cancion) throws IOException {
        listaCanciones.eliminarDato(cancion[0].artista, cancion[0].nombreCancion);
        sincronizarListaToJson();

    }

    public int largocanciones() {
        System.out.println(canciones.length);
        return canciones.length;
    }

    public boolean almacenarCacion() {
        boolean almacenadoExito = true;
/**
 * Que al entrar lo convierta en Json y agregar una lista
 */


        return almacenadoExito;
    }


}

