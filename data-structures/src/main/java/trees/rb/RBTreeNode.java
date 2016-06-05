package trees.rb;

/**
 * @author Jiri
 */
public class RBTreeNode<Type extends Comparable<Type>> {


    public enum Color {
        RED, BLACK;

        public static Color of(RBTreeNode node) {
            if (node == null) {
                return BLACK;
            }
            return node.coloer;
        }
    }

    private Color coloer;
    private Type value;

    private RBTreeNode<Type> left;
    private RBTreeNode<Type> right;
    private RBTreeNode<Type> parent;

    public RBTreeNode(Type value, RBTreeNode<Type> left, RBTreeNode<Type> right, RBTreeNode<Type> parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.coloer = Color.RED;
    }

    public boolean contains(Type v) {
        if (v.compareTo(value) > 0) { //v > value
            return right != null && right.contains(v);
        }
        // v < value
        return v.compareTo(value) == 0 || left != null && left.contains(v);

    }

    public RBTreeNode<Type> add(Type value) {
        if (value.compareTo(this.value) == 0) {
            return this;
        }
        if (value.compareTo(this.value) > 0) { //value > this.value
            if (right != null) {
                right.add(value);
            } else {
                right = new RBTreeNode<>(value, null, null, this);
                right.balanceInsert();
            }
        } else {
            if (left != null) {
                left.add(value);
            } else {
                left = new RBTreeNode<>(value, null, null, this);
                left.balanceInsert();
            }

        }
        return root();
    }


    public <Type extends Comparable<Type>> RBTreeNode<Type> remove(Type value) {
        return null;
    }

    private RBTreeNode<Type> root() {
        RBTreeNode<Type> n = this;
        while (n.parent != null) {
            n = n.parent;
        }
        return n;
    }


    public void balanceInsert() {
        if (coloer.equals(Color.BLACK)) {
            return;
        }

        if (parent == null) {
            coloer = Color.BLACK;
            return;
        }
        if (parent.coloer.equals(Color.BLACK)) {
            return;
        }
        RBTreeNode<Type> uncle = uncle();
        RBTreeNode<Type> grandParent = grandParent();

        if (Color.of(uncle).equals(Color.RED)) {
            parent.coloer = Color.BLACK;
            uncle.coloer = Color.BLACK;
            grandParent.coloer = Color.RED;
            grandParent.balanceInsert();

        }

        if (this == parent.right && parent == grandParent.left) {
            parent.rotateLeft();
            left.insertCase5();
        } else if (this == parent.left && parent == grandParent.right) {
            parent.rotateRight();
            right.insertCase5();
        }

    }

    private void insertCase5() {
        RBTreeNode<Type> grandParent = grandParent();
        parent.coloer = Color.BLACK;
        grandParent.coloer = Color.RED;
        if (this == parent.left) {
            grandParent.rotateRight();
        } else {
            grandParent.rotateLeft();
        }
    }

    private void rotateLeft() {
        RBTreeNode<Type> gp = parent;
        RBTreeNode<Type> l = this;
        RBTreeNode<Type> lr = right.left;
        RBTreeNode<Type> p = right;


        if (gp != null) {
            if (this == gp.left) {
                gp.left = p;
            } else {
                gp.right = p;
            }
        }

        l.right = lr;
        if (lr != null) {
            lr.parent = l;
        }

        p.left = l;
        l.parent = p;

        p.parent = gp;

    }

    private void rotateRight() {
        RBTreeNode<Type> gp = parent;
        RBTreeNode<Type> p = left;
        RBTreeNode<Type> r = this;
        RBTreeNode<Type> rl = left.right;

        if (gp != null) {
            if (this == gp.left) {
                gp.left = p;
            } else {
                gp.right = p;
            }
        }
        r.left = rl;
        if (rl != null) {
            rl.parent = r;
        }

        p.right = r;
        r.parent = p;

        p.parent = gp;
    }

    RBTreeNode<Type> sibling() {
        if (parent.left == this) {
            return parent.right;
        } else {
            return parent.left;
        }

    }

    RBTreeNode<Type> grandParent() {
        if (parent != null) {
            return parent.parent;
        }
        return null;
    }

    RBTreeNode<Type> uncle() {
        RBTreeNode<Type> grandParent = grandParent();
        if (grandParent == null) {
            return null;
        }
        if (parent == grandParent.left) {
            return grandParent.right;
        } else {
            return grandParent.left;
        }
    }
}
