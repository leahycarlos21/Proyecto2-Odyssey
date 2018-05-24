package com.tec.datos1.ArbolAVL;
import com.tec.datos1.XMLCancion.Canciones;

public class AVLNode {

    public int data;
    public AVLNode left;
    public AVLNode right;
    public int height;

    public AVLNode(int data) {
        this.data = data;
        height = 1;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
