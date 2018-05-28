package com.tec.datos1.ArbolSplay;

public class SplayNode {
    public SplayNode right;
    public SplayNode left;
    public SplayNode parent;
    public String[] data;

    public SplayNode()
    {
        this(null, null, null, null);
    }

    public SplayNode(String[] data)
    {
        this(data, null, null, null);
    }

    public SplayNode(String[] data, SplayNode left, SplayNode right, SplayNode parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.data = data;
    }
}
