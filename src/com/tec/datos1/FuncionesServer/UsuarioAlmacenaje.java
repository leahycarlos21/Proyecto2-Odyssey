package com.tec.datos1.FuncionesServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ArbolUsuarios.ArbolBinarioBusqueda;
import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.XMLUsuario.Usuario;
import java.io.File;
import java.io.IOException;

public class UsuarioAlmacenaje {
    ArbolBinarioBusqueda arbolUsuario = new ArbolBinarioBusqueda();
    File archivo = new File("Usuarios.json");
    ObjectMapper objectmapper = new ObjectMapper();
    Usuario usuario;

    public UsuarioAlmacenaje() throws IOException {

        try {
            //sincronizarJsonToLista();
            //listaCanciones.imprimir();
        }catch(Exception e){

        }

    }

    /**
     * Añade el array de canciones entrante a la lista
     */
    public void addUsuarioEntrante(Usuario usuarioEntrante) throws IOException {
        arbolUsuario.addNodo(usuarioEntrante);
        //sincronizarArbolToJson();
        arbolUsuario.inOrden(arbolUsuario.raiz);
        }



    }

    //private void sincronizarJsonToArbol() throws IOException {
    //    Usuarios usuarios = objectmapper.readValue(archivo, usuario.class);
    //    for (int i = 0; i < canciones.length; i++) {
      //      listaCanciones.insertar(1, canciones[i]);
        //}
    //}

    //private void sincronizarArbolToJson() throws IOException {
      //  Canciones[] cancionesLista = new Canciones[listaCanciones.cantidad()];
       // int num = 0;
       // while (num < cancionesLista.length) {
         //   cancionesLista[num] = listaCanciones.obtenerDato(num + 1);
          //  num++;
       // }
       // objectmapper.writerWithDefaultPrettyPrinter().writeValue(archivo, cancionesLista);

    //}

    //public boolean almacenarCacion() {
      //  boolean almacenadoExito = true;
/**
 * Que al entrar lo convierta en Json y agregar una lista
 */


      //  return almacenadoExito;
//    }

