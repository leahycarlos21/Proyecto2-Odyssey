package com.tec.datos1.ArbolSplay;

public class SplayNode {
    public SplayNode right;
    public SplayNode left;
    public SplayNode parent;
    public int data;

    public SplayNode()
    {
        this(0, null, null, null);
    }

    public SplayNode(int data)
    {
        this(data, null, null, null);
    }

    public SplayNode(int data, SplayNode left, SplayNode right, SplayNode parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.data = data;
    }


}
