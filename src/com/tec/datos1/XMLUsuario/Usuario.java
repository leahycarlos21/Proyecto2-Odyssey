package com.tec.datos1.XMLUsuario;

public class Usuario {
    public String nickName;
    public String password;
    public String nombre;
    public String apellido;
    public String edad;
    public Usuario hojaIzquierda;
    public Usuario hojaDerecha;
    //public  [] mensajes;


    public Usuario(String nickName, String password, String nombre, String apellido, String edad){
        this.nickName = nickName;
        this.password = password;
        this.nombre = nombre;
        this. apellido = apellido;
        this.edad = edad;
    }
    public Usuario(){

    }

    public Usuario(String id){
        this.nickName= id;

    }

    public String getNickName() {

        return nickName;
    }

    public void setNickName(String nickName)

    {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Usuario getHojaIzquierda() {
        return hojaIzquierda;
    }

    public void setHojaIzquierda(Usuario hojaIzquierda) {
        this.hojaIzquierda = hojaIzquierda;
    }

    public Usuario getHojaDerecha() {
        return hojaDerecha;
    }

    public void setHojaDerecha(Usuario hojaDerecha) {
        this.hojaDerecha = hojaDerecha;
    }
}
