package com.tec.datos1.FuncionesServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ArbolUsuarios.ArbolBinarioBusqueda;
import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.XMLUsuario.Usuario;
import java.io.File;
import java.io.IOException;
import java.time.Clock;

public class UsuarioAlmacenaje {
    ArbolBinarioBusqueda arbolUsuario = new ArbolBinarioBusqueda();
    File archivoUsuario = new File("Usuarios.json");
    ObjectMapper objectmapper = new ObjectMapper();
    Usuario[] usuarios;
    Usuario root = arbolUsuario.getRaiz();

    public UsuarioAlmacenaje() throws IOException {

        try {
            sincronizarJsonToArbol();
            arbolUsuario.inOrden(arbolUsuario.getRaiz());
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

    public String search(Usuario root, String key)
    {
        if (root==null || root.nickName == key)
            return root.nickName;
        if (root.nickName.compareTo(key) > 0)
            return search(root.getHojaIzquierda(), key);
        return search(root.getHojaDerecha(), key);
    }

    public Usuario getRoot() {
        return root;
    }

    public void setRoot(Usuario root) {
        this.root = root;
    }

}





