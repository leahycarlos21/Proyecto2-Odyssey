package com.tec.datos1.ArbolAVL;
import com.tec.datos1.XMLCancion.Canciones;

public class AVLNode {

   public int data;
   public AVLNode right;
   public AVLNode left;
   public int altura;

   public AVLNode(int data){

   }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
