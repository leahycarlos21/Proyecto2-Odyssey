package com.tec.datos1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tec.datos1.FuncionesServer.CancionAlmacenaje;
import org.jmusixmatch.MusixMatchException;


import javax.sound.sampled.AudioInputStream;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    //https://dzone.com/articles/parse-xml-to-java-objects-using-jackson
    //http://www.baeldung.com/jackson-xml-serialization-and-deserialization
    public static void main(String[] args) throws IOException {
        CancionAlmacenaje almacenaje = new CancionAlmacenaje();


        /**Definicion del ServerSocket*/
        ServerSocket serverSocket = new ServerSocket(5000, 10);
        System.out.println("El servidor esta activo ....");

        while (true) {
            /**clienteSocket es el socket entrante o el socket emisor*/
            Socket clienteSocket = serverSocket.accept();
            /**BufferReader se encarga de leer lo que envió el socket*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            try {


                /** Se asigna el dato entrante al var mensajeRecibido */
                String mensajeRecibido = reader.readLine();

                /**Invoca el metodo convertirXmlStringAObject*/
                AddDatoMensaje mensaje = (AddDatoMensaje) convertirXmlStringAObject(mensajeRecibido, AddDatoMensaje.class);
                System.out.println("El codigo de operacion es " + mensaje.OpCod);


                /**EL objeto para enviar algo al servidor*/
                PrintWriter printWriter = new PrintWriter(clienteSocket.getOutputStream(), true);

                if (mensaje.OpCod.equals("01")) {
                    /**Almacenar Cancion*/
                    int num = 0;
                    //  System.out.print("---------"+mensaje.cancion[0].nombreCancion);

                    almacenaje.addCancionesEntrantes(mensaje.cancion);
                    //  System.out.print(mensaje.cancion[0].bytesSong);

                    printWriter.print("Cancion recibida del Servidor");
                    printWriter.close();

                    /*while (num<mensaje.cancion.length) {

                       System.out.println(" El artista  es " + mensaje.cancion[num].artista);
                       System.out.println("la cancion  es" + mensaje.cancion[num].nombreCancion);
                       System.out.println("la genero  es" + mensaje.cancion[num].genero);
                       System.out.println("la album  es" + mensaje.cancion[num].album);
                       System.out.println("la cantidad bytes  es" + mensaje.cancion[num].bytesSong.length);
                       printWriter.print("Cancion recibida del Servidor");
                       num++;
                   }*/

                } else if (mensaje.OpCod.equals("02")) {
                    /**Almacena un usuario*/

                } else if (mensaje.OpCod.equals("03")) {
                    AddDatoMensaje enviarDatin = new AddDatoMensaje();

                    enviarDatin.cancion = almacenaje.enviarTodaCanciones(mensaje.cantidadTotalSong);
                    // enviarDatin.cantidadTotalSong = 0;
                    System.out.println(almacenaje.largocanciones());
                    enviarDatin.cantidadTotalSong = almacenaje.largocanciones();


                    enviarDatin.OpCod = "03";

                    // String enviar = write2XMLString(enviarDato.cancion);
                    String enviar = whenJavaSerializedToXmlStr_thenCorrect(enviarDatin.cancion);
                    enviar = enviar.replaceAll("\n", "").replaceAll("\r", "").replaceAll("Cancioneses",
                            "Canciones").replaceAll("<item>", "<Canciones>").replaceAll("</item>", "</Canciones>");

                    printWriter.println(enviar);
                    printWriter.close();
                } else if (mensaje.OpCod.equals("04")) {
                    AddDatoMensaje enviarDatis = new AddDatoMensaje();

                    enviarDatis.cancion = almacenaje.enviarCancionSolicitad(mensaje.cancion[0].artista, mensaje.cancion[0].nombreCancion);
                    enviarDatis.OpCod = "04";
                    System.out.println("la vara es" + enviarDatis.cancion[0].bytesSong);
                    String enviaris = whenJavaSerializedToXmlStr_thenCorrect(enviarDatis.cancion);
                    //String enviaris = whenJavaSerializedToXmlStr_thenCorrect(enviarDatis.cancion);

                    enviaris = enviaris.replaceAll("\n", "").replaceAll("\r", "").replaceAll("Cancioneses",
                            "Canciones").replaceAll("<item>", "<Canciones>").replaceAll("</item>", "</Canciones>");
                    //System.out.print("---" + enviaris);

                    printWriter.println(enviaris);

                    printWriter.close();
                } else if (mensaje.OpCod.equals("05")) {
                    almacenaje.eliminarCancion(mensaje.cancion);
                    printWriter.println("Canción eliminada");


                } else if (mensaje.OpCod.equals("06")) {//06
                    almacenaje.editarCancion(mensaje.cancion);
                    printWriter.println("Canción editada");

                }else if (mensaje.OpCod.equals("07")) {
                    try {
                        almacenaje.editarMusixMatch(mensaje.cancion);
                    } catch (MusixMatchException e) {
                        e.printStackTrace();
                    }
                    printWriter.println("Canción editada");

                }
                else {
                    System.out.print("whut");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            clienteSocket.close();


        }


    }

    /**
     * http://javasampleapproach.com/java/jackson-convert-java-xml-file-xml-string
     */
    public static Object convertirXmlStringAObject(String xmlString, Class<?> cls)
            throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        Object object = xmlMapper.readValue(xmlString, cls);
        return object;
    }

    public static String write2XMLString(Object object)
            throws JsonProcessingException {

        XmlMapper xmlMapper = new XmlMapper();
        // use the line of code for pretty-print XML on console. We should remove it in production.
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return xmlMapper.writeValueAsString(object);
    }

    public static String whenJavaSerializedToXmlStr_thenCorrect(Object object) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(object);
        return xml;
    }


}

