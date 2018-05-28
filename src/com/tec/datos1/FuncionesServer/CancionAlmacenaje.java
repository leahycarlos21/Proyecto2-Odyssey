package com.tec.datos1.FuncionesServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.Ordenamientos.BubbleSort;
import com.tec.datos1.Ordenamientos.QuickSort;
import com.tec.datos1.Ordenamientos.RadixSort;
import com.tec.datos1.XMLCancion.Canciones;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;

public class CancionAlmacenaje {
    private ListaDoble listaCanciones = new ListaDoble();
    private File archivo = new File("Canciones.json");
    private Canciones[] canciones;
    private int ID;
    String apiKey = "f49c29ea71404d6b85b6b514d19df23e";
    MusixMatch musixMatch = new MusixMatch(apiKey);
    public CancionAlmacenaje() {

        try {

            sincronizarJsonToLista();
            ID=obtenerIDAutomatic();
            System.out.println("Num "+ID);
            bubbleSort();


           // listaCanciones.imprimir();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Busca el ID mayor para actualizar
     * @return
     */
    public int obtenerIDAutomatic(){
        int numMayor=canciones[0].ID;
        for(int i =0;i<canciones.length;i++){
            if (canciones[i].ID>numMayor){
                numMayor=canciones[i].ID;
                System.out.println("nUM --------"+numMayor);
            }
        }
        return numMayor+1;
    }

    public Canciones[] enviarCancionSolicitad(String artista, String cancion) {
        Canciones[] listaenviar = new Canciones[1];

        listaenviar[0] = listaCanciones.obtenerDato(artista, cancion).getDato();
        System.out.println("Enviar cancion solicitada" + listaenviar[0].getNombreCancion());



        return listaenviar;
    }

    public void editarCancion(Canciones[] listEdit) throws Exception {
        eliminarCancion(listEdit);

        Canciones[] auxArray = new Canciones[1];
        auxArray[0]=listEdit[1];
        addCancionesEntrantes(auxArray);

    }

    public void editarMusixMatch (Canciones[] cancionEntrante) throws Exception {
        String cancionNombre = cancionEntrante[0].nombreCancion;
        String artistNombre = cancionEntrante[0].artista;
        eliminarCancion(cancionEntrante);
        Track datosCancion = musixMatch.getMatchingTrack(cancionNombre,artistNombre);
        TrackData data = datosCancion.getTrack();

        cancionEntrante[0].artista=data.getArtistName();
        cancionEntrante[0].album=data.getAlbumName();


        System.out.println("AlbumID : "    + data.getAlbumId());
        System.out.println("Album Name : " + data.getAlbumName());
        System.out.println("Artist ID : "  + data.getArtistId());
        System.out.println("Artist Name : " + data.getArtistName());
        System.out.println("Track ID : "   + data.getTrackId());


        int CancionID = data.getTrackId();
        Lyrics letra = musixMatch.getLyrics(CancionID);
        cancionEntrante[0].letra=letra.getLyricsBody();
        System.out.println("Lyrics Body     : "     + letra.getLyricsBody());
        System.out.println("Lyrics Body     : " + letra.getLyricsLang());
        addCancionesEntrantes(cancionEntrante);


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
    public void addCancionesEntrantes(Canciones[] cancionesEntrantes) throws Exception {
        int num = 0;
        while (num < cancionesEntrantes.length) {
            if (listaCanciones.existe(cancionesEntrantes[num]) == false) {
                System.out.println("Entro!!!!! " + cancionesEntrantes[num].nombreCancion);

                cancionesEntrantes[num].ID=ID;
                ID++;
                listaCanciones.add(cancionesEntrantes[num]);
            }
            num++;
        }
        System.out.println(listaCanciones.getInPosition(0).getDato().artista);

         sincronizarListaToJson();


        // listaCanciones.imprimir();

    }
    private void sincronizarListaToArray() throws Exception {
         canciones= new Canciones[listaCanciones.Size()];
        int num = 0;
        while (num < canciones.length) {
            canciones[num] = listaCanciones.getInPosition(num).getDato();
            num++;
        }
    }
    private void sincronizarJsonToLista() throws IOException {
        ObjectMapper objectmapper = new ObjectMapper();

        canciones = objectmapper.readValue(archivo, Canciones[].class);
        for (int i = 0; i < canciones.length; i++) {
            listaCanciones.add( canciones[i]);
           System.out.println(canciones[i].artista);


        }

    }

    private void sincronizarListaToJson() throws Exception {
        sincronizarListaToArray();
        ObjectMapper objectmapper = new ObjectMapper();

        Canciones[] cancionesaJson = new Canciones[listaCanciones.Size()];
        int num = 0;
        while (num < cancionesaJson.length) {
            cancionesaJson[num] = listaCanciones.getInPosition(num).getDato();
            num++;
        }
        objectmapper.writeValue(archivo, cancionesaJson);

    }

    public void eliminarCancionis(Canciones cancion) throws Exception {
        int posEliminar = listaCanciones.obtenerPos(cancion.artista, cancion.nombreCancion);
        listaCanciones.remove(posEliminar);
        sincronizarListaToJson();
    }
    public void eliminarCancion(Canciones[] cancion) throws Exception {
        int posEliminar=listaCanciones.obtenerPos(cancion[0].artista, cancion[0].nombreCancion);
        System.out.println("pos eliminar"+posEliminar);

        listaCanciones.remove(posEliminar);
        System.out.println("CANTIDAD MAEEE"+listaCanciones.Size());

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
    /**Ordenada los albums */
    public void bubbleSort() throws Exception {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(listaCanciones);
        sincronizarListaToArray();
    }
    /**Ordenar por cancion*/
    public void QuickSort(){
        QuickSort quickSort = new QuickSort();
        quickSort.QuickSort(canciones);

    }

    public void RadixSort(){
        RadixSort radixSort = new RadixSort();
        radixSort.RadixSort(canciones);
    }


}

