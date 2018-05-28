package com.tec.datos1.ArbolSplay;

public class SplayTree {
    public SplayNode root;
    public int count = 0;

    public void insert(String[] data)
    {
        SplayNode z = root;
        SplayNode p = null;
        while (z != null)
        {
            p = z;
            if (data[0].compareTo(p.data[0]) > 0)
                z = z.right;
            else
                z = z.left;
        }
        z = new SplayNode();
        z.data = data;
        z.parent = p;
        if (p == null)
            root = z;
        else if (data[0].compareTo(p.data[0]) > 0)
            p.right = z;
        else
            p.left = z;
        Splay(z);
        count++;
    }
    // Rotacion
    public void makeLeftChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
            throw new RuntimeException("WRONG");

        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.right != null)
            c.right.parent = p;

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    //Rotacion
    public void makeRightChildParent(SplayNode c, SplayNode p)
    {
        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
            throw new RuntimeException("WRONG");
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null)
            c.left.parent = p;
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    //Funcion Splay
    private void Splay(SplayNode x)
    {
        while (x.parent != null)
        {
            SplayNode Parent = x.parent;
            SplayNode GrandParent = Parent.parent;
            if (GrandParent == null)
            {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            }
            else
            {
                if (x == Parent.left)
                {
                    if (Parent == GrandParent.left)
                    {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    else
                    {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else
                {
                    if (Parent == GrandParent.left)
                    {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    }
                    else
                    {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }
    public String search(String ele)
    {
        SplayNode z = root;
        while (z != null)
        {
            if (ele.compareTo(z.data[0]) > 0)
                z = z.right;
            else if (ele.compareTo(z.data[0]) < 0)
                z = z.left;
            else
                return z.data[1];
        }
        return null;
    }

    public void inorder()
    {
        inorder(root);
    }
    private void inorder(SplayNode r)
    {
        if (r != null)
        {
            inorder(r.left);
            System.out.print(r.data[0] +" ");
            inorder(r.right);
        }
    }

}
