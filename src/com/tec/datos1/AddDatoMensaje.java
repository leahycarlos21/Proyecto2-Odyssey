package com.tec.datos1;

import com.tec.datos1.Mensajes;
import com.tec.datos1.XMLCancion.Canciones;
import com.tec.datos1.XMLUsuario.Usuario;

/**
 *Clase necesaria para crear el objeto del XML recbido
 * Tiene que tener el mismo formato de la clase de C# para poder hacer el intercambio
 */
public class AddDatoMensaje extends Mensajes {
    public Canciones[] cancion;
    public Usuario usuario;

    public AddDatoMensaje(){

    }
}
