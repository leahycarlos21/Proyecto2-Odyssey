package com.tec.datos1;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tec.datos1.FuncionesServer.CancionAlmacenaje;
import com.tec.datos1.FuncionesServer.UsuarioAlmacenaje;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    //https://dzone.com/articles/parse-xml-to-java-objects-using-jackson
    //http://www.baeldung.com/jackson-xml-serialization-and-deserialization
    public static void main(String[] args) throws IOException {
        CancionAlmacenaje almacenaje = new CancionAlmacenaje();
        UsuarioAlmacenaje almaceajeUsuario =  new UsuarioAlmacenaje();

        /**Definicion del ServerSocket*/
        ServerSocket serverSocket = new ServerSocket(5001, 10);
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
                   int num =0;
                    System.out.println("largo"+mensaje.cancion.length);
                    almacenaje.addCancionesEntrantes(mensaje.cancion);

                    /*while (num<mensaje.cancion.length) {

                       System.out.println(" El artista  es " + mensaje.cancion[num].artista);
                       System.out.println("la cancion  es" + mensaje.cancion[num].nombreCancion);
                       System.out.println("la genero  es" + mensaje.cancion[num].genero);
                       System.out.println("la album  es" + mensaje.cancion[num].album);
                       System.out.println("la cantidad bytes  es" + mensaje.cancion[num].bytesSong.length);
                    printWriter.print("Cancion recibida del Servidor");
                    num++;
                }*/
                printWriter.close();
            }
                else if ( mensaje.OpCod.equals("02")){
                System.out.println("entro");
                almaceajeUsuario.addUsuarioEntrante(mensaje.usuario);

                }
                else if (mensaje.OpCod.equals("06")){
                    System.out.println("entro");
                    


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
            throws JsonParseException, JsonMappingException, IOException {

        XmlMapper xmlMapper = new XmlMapper();
        Object object = xmlMapper.readValue(xmlString, cls);
        return object;
    }


}

