package com.tec.datos1.ArbolAVL;

public class AVLNode {
    public String[] data;
    public AVLNode left;
    public AVLNode right;
    public int height;

    public AVLNode(String[] data) {
        this.data = data;
        height = 1;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
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
