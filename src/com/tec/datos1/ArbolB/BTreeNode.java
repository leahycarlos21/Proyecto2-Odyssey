package com.tec.datos1.ArbolB;

public class BTreeNode {
    private int order; //determina el orden del arbol
    private int countKeys; //cuenta numero de llaves en el nodo
    private String[] keys; //arreglo que contiene las llaves
    private BTreeNode[] child; //contiene las referencias a los hijos
    private boolean isLeaf;
    private BTreeNode parent; //referencia al nodo padre
    /**
     * Constructor de la clase
     * @param order
     * @param parent
     */
    public BTreeNode(int order, BTreeNode parent) {
        this.order = order;
        this.parent = parent;
        this.keys = new String[2*order-1];
        this.child = new BTreeNode[2*order];
        this.isLeaf = true;
        this.countKeys = 0;

    }
    /**
     * GETTERS Y SETTERS
     * @return
     */
    public int getOrder() {
        return order;
    }

    public void setOrder(int numChildren) {
        this.order = numChildren;
    }

    public String getValue(int index) {
        return keys[index];
    }

    public void setValue(String value, int index) {
        this.keys[index] = value;
    }

    public int getCountKeys() {
        return countKeys;
    }

    public void setCountKeys(int countKeys) {
        this.countKeys = countKeys;
    }

    public BTreeNode getChild(int index) {
        return child[index];
    }

    public void setChild(BTreeNode child, int index) {
        this.child[index] = child;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public BTreeNode getParent() {
        return parent;
    }

    public void setParent(BTreeNode parent) {
        this.parent = parent;
    }

}
