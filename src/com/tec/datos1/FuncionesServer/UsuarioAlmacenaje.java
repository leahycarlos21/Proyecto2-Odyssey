package com.tec.datos1.FuncionesServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tec.datos1.ListaCanciones.ListaDoble;
import com.tec.datos1.XMLUsuario.Usuario;
import java.io.File;
import java.io.IOException;

public class UsuarioAlmacenaje {
    ListaDoble listaUsuario = new ListaDoble();
    File usuarioJSON = new File("Usuarios.json");
    ObjectMapper objectmapper = new ObjectMapper();
    Usuario[] usuarios;

    public UsuarioAlmacenaje() throws IOException {

    }
    //IMPORTANTE SIRVE
    private void sincronizarJsonToLista() throws IOException {
        Usuario[] usuarios  = objectmapper.readValue(usuarioJSON, Usuario[].class);
        for (int i = 0; i < usuarios.length; i++) {
            //listaUsuario.insertar(1, usuarios[i]);
        }
    }
    //IMPORTANTE SIRVE
    private void sincronizarListaToJson() throws IOException {
        Usuario[] cancionesLista = new Usuario[listaUsuario.cantidad()];
        int num = 0;
        while (num < cancionesLista.length) {
            //cancionesLista[num] = listaUsuario.obtenerDato(num + 1);
            num++;
        }
        objectmapper.writerWithDefaultPrettyPrinter().writeValue(usuarioJSON, cancionesLista);

    }
}
