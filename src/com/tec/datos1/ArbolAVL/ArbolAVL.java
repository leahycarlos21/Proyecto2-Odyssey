package com.tec.datos1.ArbolAVL;

public class ArbolAVL {
    public int getBalance(AVLNode n) {
        if (n != null) {
            return (getHeight(n.left) - getHeight(n.right));
        }
        return 0;
    }

    public int getHeight(AVLNode n) {
        if (n != null) {
            return n.height;
        }
        return 0;
    }
    //Metodo para hacer la rotacion hacia la izquierda
    public AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        //Hace la rotacion
        x.right = y;
        y.left = T2;

        //Actualiza la altura del arbol
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return x;
    }
    //Metodo para realizar la rotacion hacia la izquierda
    public AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        //Hace la rotacion
        y.left = x;
        x.right = T2;

        //Actualiza sus alturas
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }
    // Metodo para la insercion de un dato al arbol
    // tomando en cuenta las alturas y si necesita
    // hacer algun tipo de rotacion

    public AVLNode insert(AVLNode node, int data) {
        if (node == null) {
            return (new AVLNode(data));
        }
        if (node.data > data) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }
        // Actualiza la altura del nodo
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        int balDiff = getBalance(node);

        // Rotacion left
        if (balDiff > 1 && node.left.data > data ) {
            return rightRotate(node);
        }

        // Rotacion Right
        if (balDiff < -1 && node.right.data < data) {
            return leftRotate(node);
        }

        // Rotacion Left-Right
        if (balDiff > 1 && node.left.data < data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Rotacion Right-Left
        if (balDiff < -1 && node.right.data > data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    public int search(AVLNode root, int key)
    {
        if (root==null || root.data == key)
            return key;
        if (root.data > key)
            return search(root.left, key);
        return search(root.right, key);
    }

    //Metodo para la representar el arbol en orden de menor a mayor
    public void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(" " + root.data);
            inorder(root.right);
        }
    }
}


