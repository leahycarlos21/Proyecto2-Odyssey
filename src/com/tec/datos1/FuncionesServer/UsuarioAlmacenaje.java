package com.tec.datos1.FuncionesServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ArbolUsuarios.ArbolBinarioBusqueda;
import com.tec.datos1.XMLUsuario.Usuario;

import java.io.File;
import java.io.IOException;

public class UsuarioAlmacenaje {
    ArbolBinarioBusqueda arbolUsuario = new ArbolBinarioBusqueda();
    File archivoUsuario = new File("Usuarios.json");
    ObjectMapper objectmapper = new ObjectMapper();
    Usuario[] usuarios;
    public Usuario raiz;


    public UsuarioAlmacenaje() throws IOException {

        try {
            sincronizarJsonToArbol();
        } catch (Exception e) {

        }

    }

    /**
     * Añade el array de canciones entrante a la lista
     */
    public void addUsuarioEntrante(Usuario usuarioEntrante) throws IOException {
        arbolUsuario.addNodo(usuarioEntrante);
        //sincronizarArbolToJson();
        arbolUsuario.inOrden(arbolUsuario.raiz);
        sincronizarArbolToJson();
    }


    private void sincronizarArbolToJson() throws IOException {
        ObjectMapper objectmapper = new ObjectMapper();
        Usuario[] usuarioArbol = arbolUsuario.arbolToArrayAux(arbolUsuario.getRaiz());
        System.out.println(usuarioArbol[0].nickName);
        objectmapper.writerWithDefaultPrettyPrinter().writeValue(archivoUsuario, usuarioArbol);
    }

    private void sincronizarJsonToArbol() throws IOException {
        Usuario[] usuarios = objectmapper.readValue(archivoUsuario, Usuario[].class);
        for (int i = 0; i < usuarios.length; i++) {
            arbolUsuario.addNodo(usuarios[i]);
        }
    }

    public ArbolBinarioBusqueda getArbolUsuario() {
        return arbolUsuario;
    }

    public void setArbolUsuario(ArbolBinarioBusqueda arbolUsuario) {
        this.arbolUsuario = arbolUsuario;
    }


    public Usuario getRaiz() {
        return raiz;
    }

    public void setRaiz(Usuario raiz) {
        this.raiz = raiz;
    }

    public boolean find(String nickName, String password) {
        System.out.println("Entrante"+nickName+password);
        Usuario current = arbolUsuario.getRaiz();
        while (current != null) {
            if (current.nickName.equals(nickName) && current.password.equals(password)) {
                return true;
            } else if (current.nickName.compareTo(nickName) > 0) {
                current = current.getHojaIzquierda();
            } else {
                current = current.getHojaDerecha();
            }

        }
        return false;
    }
}




