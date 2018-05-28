package com.tec.datos1.ArbolB;

public class BTree {
    private BTreeNode root;
    private int size;
    private static int order;
    int count = 0;
    String[] arr;

    //Constructor de la clase

    public BTree(int order) {
        BTree.order = order;
        this.root = new BTreeNode(order, null);
        this.size = 0;
    }

    //Establece raiz del arbol

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    //Obtiene la raiz del arbol

    public BTreeNode getRoot() {
        return this.root;
    }
    /**
     * Se encarga de dividir de la pagina en caso de que este llena
     * @param nodeA
     * @param i
     * @param nodeB
     */
    public void split(BTreeNode nodeA, int i, BTreeNode nodeB) {
        BTreeNode nodeC = new BTreeNode(order, null);

        nodeC.setLeaf(nodeB.isLeaf());
        nodeC.setCountKeys(order - 1);

        for(int j = 0; j < order-1; j++) {
            nodeC.setValue(nodeB.getValue(j+order), j);//Copia el ultimo elemento de B, en la 1er pos de C
        }

        if(!nodeB.isLeaf()) { //Si el nodo no es hoja tenemos que reacomodar los nodos hijos
            for(int k = 0; k < order; k++) {
                nodeC.setChild(nodeB.getChild(k+order),k);//recolocando el hijo de B
            }
        }
        nodeB.setCountKeys(order-1);

        for(int j = nodeA.getCountKeys(); j > i; j--) {
            nodeA.setChild(nodeA.getChild(j), j+1);
        }
        nodeA.setChild(nodeC, i+1);

        for(int j = nodeA.getCountKeys(); j > i; j--) {
            nodeA.setValue(nodeA.getValue(j), j+1);
        }
        nodeA.setValue(nodeB.getValue(order-1), i);

        nodeB.setValue("", order-1);

        for(int j = 0; j < order - 1; j++) {
            nodeB.setValue("", j + order);
        }

        nodeA.setCountKeys(nodeA.getCountKeys()+1);
    }
    /**
     * Se encarga de insertar el nodo en el arbol B
     * @param key
     */
    public void insertNode(String key) {
        BTreeNode r = this.root;
        if(r.getCountKeys() == (2*order-1)) { // la pagina esta llena
            BTreeNode newNode = new BTreeNode(order, null);

            this.root = newNode;
            newNode.setLeaf(false);
            newNode.setCountKeys(0);
            newNode.setChild(r, 0);

            split(newNode, 0, r);
            nonFullInsert(newNode, key);
        }else {
            nonFullInsert(r, key);
        }
        this.size++;
    }
    /**
     * Se encarga de realizar la insercion si la pagina no esta llena, funcion auxiliar del inserNode
     * @param node
     * @param key
     */
    public void nonFullInsert(BTreeNode node, String key) {
        int numKeys = node.getCountKeys();

        if(node.isLeaf()) {
            while(numKeys >= 1 && key.compareToIgnoreCase(node.getValue(numKeys-1)) < 0) { //Aqui encuentra un espacio para almacenar la llave
                node.setValue(node.getValue(numKeys-1), numKeys); //cambia los valores de lugar para abrir espacio al nuevo nodo
                numKeys--;
            }

            node.setValue(key, numKeys);//Asigna la llave al nodo
            node.setCountKeys(node.getCountKeys()+1); //incrementa la cantidad de llaves del nodo
        }else {
            int j = 0;
            while(j < node.getCountKeys() && key.compareToIgnoreCase(node.getValue(j)) > 0) { //busca el lugar para aplicar recursividad al hijo correcto
                j++;
            }

            if(node.getChild(j).getCountKeys() == (order*2 - 1)) { //Si la pagina esta llena
                split(node, j, node.getChild(j)); //llama al split del nodo con su hijo
                if(key.compareToIgnoreCase(node.getValue(j)) > 0) {
                    j++;
                }
            }
            nonFullInsert(node.getChild(j), key);
        }
    }
    /**
     * Se encarga de buscar el nodo en el aerbol B, a partir de su llave
     * @param root
     * @param key
     * @return BTreeNode
     */
    public BTreeNode searchNode(BTreeNode root, String key) {
        int i = 0;
        while(root.getCountKeys() > i && key.compareToIgnoreCase(root.getValue(i)) > 0) {
            i++;
        }

        if(i <= root.getCountKeys() && key.compareToIgnoreCase(root.getValue(i)) == 0) {
            return root;

        }

        if(root.isLeaf()) {
            return null;
        }
        else {
            return searchNode(root.getChild(i), key);
        }
    }
    /**
     * Se encarga de borrar la llave de la pagina
     * @param key
     */
    public void deleteKey(String key) {
        BTreeNode temp = new BTreeNode(order, null);
        temp = searchNode(this.root, key);

        if(temp.isLeaf() && temp.getCountKeys() > (order-1)){
            int i = 0;
            while(key.compareToIgnoreCase(temp.getValue(i)) > 0) {
                i++;
            }

            for(int j = i; j < (2*order - 2); j++) {
                temp.setValue(temp.getValue(j+1), j);
            }
            temp.setCountKeys(temp.getCountKeys()-1);
        }
    }

    /**
     * LLama a la funcion principal para recorrer el arbol
     * @return array
     */
    public String[] traverseTree() {
        arr = new String[this.size];
        traverse(this.root);
        return arr;
    }

    //Funcion principal para recorrer el arbol

    public void traverse(BTreeNode node){
        for(int i = 0; i < node.getCountKeys(); i++){
            System.out.print(node.getValue(i)+ " "); //Aqui imprime el nodo raiz
            this.arr[this.count] = node.getValue(i);
            this.count ++;
        }

        if(!node.isLeaf()) {//ingresa si el nodo no es una hoja
            for(int j = 0; j <= node.getCountKeys()  ; j++) { //Este ciclo imprime el arbol recursivamente
                if(node.getChild(j) != null) {
                    System.out.println();
                    traverse(node.getChild(j));
                }
            }
        }
    }


}
