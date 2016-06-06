package trees.rb;

import java.lang.ProcessBuilder.Redirect;

/**
 * @author Jiri
 */
public class RBTreeNode<Type extends Comparable<Type>>
{

    public enum Color
    {
        RED, BLACK;

        public static Color of( RBTreeNode node ) {
            if( node == null ) {
                return BLACK;
            }
            return node.color;
        }
    }

    private Color color;
    private Type value;

    private RBTreeNode<Type> left;
    private RBTreeNode<Type> right;
    private RBTreeNode<Type> parent;

    public RBTreeNode( Type value, RBTreeNode<Type> left, RBTreeNode<Type> right, RBTreeNode<Type> parent ) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.color = Color.RED;
    }

    public boolean contains( Type v ) {
        if( v.compareTo( value ) > 0 ) { // v > value
            return right != null && right.contains( v );
        }
        // v < value
        return v.compareTo( value ) == 0 || left != null && left.contains( v );

    }

    public RBTreeNode<Type> add( Type value ) {
        if( value.compareTo( this.value ) == 0 ) {
            return this;
        }
        if( value.compareTo( this.value ) > 0 ) { // value > this.value
            if( right != null ) {
                right.add( value );
            } else {
                right = new RBTreeNode<>( value, null, null, this );
                right.balanceInsert();
            }
        } else {
            if( left != null ) {
                left.add( value );
            } else {
                left = new RBTreeNode<>( value, null, null, this );
                left.balanceInsert();
            }

        }
        return root();
    }

    public RBTreeNode<Type> remove( Type value ) {
        if( value.compareTo( this.value ) > 0 ) {
            if( this.right != null ) {
                return this.right.remove( value );
            } else {
                return root();
            }
        } else if( value.compareTo( this.value ) < 0 ) {
            if( this.left != null ) {
                return this.left.remove( value );
            } else {
                return root();
            }
        } else {
            if( this.left != null && this.right != null ) {
                this.value = left.max();
                return left.remove( this.value );
            }
            RBTreeNode<Type> child;
            if( left != null ) {
                child = left;
            } else {
                child = right;
            }
            if( parent != null ) {
                if( this == parent.right ) {
                    parent.right = child;
                } else {
                    parent.left = child;
                }
            }
            if( child != null ) {
                child.parent = parent;
            }

            if( this.color == Color.RED ) {
                return (parent != null) ? parent.root() : child;
            }

            if( Color.of( child ) == Color.RED ) {
                child.color = Color.BLACK;
                return child.root();
            }

            return balance( child );

        }
    }

    private RBTreeNode<Type> balance( RBTreeNode<Type> child ) {
        if( parent == null ) {
            return child;
        }

        RBTreeNode<Type> sibling = parent.right == child ? parent.left : parent.right;

        if( sibling.color == Color.RED ) {
            assert parent.color == Color.BLACK; // canot have 2 reds after each other sibling is red
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            if( parent.left == child ) {
                parent.rotateLeft();
            } else {
                parent.rotateRight();
            }
            return parent.root();
        }

        if( parent.color == Color.BLACK && sibling.color == Color.BLACK && Color.of( sibling.left ) == Color.BLACK
                && Color.of( sibling.right ) == Color.BLACK ) {
            sibling.color = Color.RED;
            return balance( parent );
        }

        if( parent.color == Color.RED && sibling.color == Color.BLACK && Color.of( sibling.left ) == Color.BLACK
                && Color.of( sibling.right ) == Color.BLACK ) {
            parent.color = Color.BLACK;
            sibling.color = Color.RED;
            return parent.root();
        }

        if( parent.left == child && sibling.color == Color.BLACK && Color.of( sibling.left ) == Color.RED ) {
            sibling.rotateRight();
        } else if( parent.right == child && sibling.color == Color.BLACK && Color.of( sibling.right ) == Color.RED ) {
            sibling.rotateLeft();
        }

        if( parent.left == child ) {
            parent.rotateLeft();
        } else {
            parent.rotateRight();
        }
        sibling.color = parent.color;
        parent.color = Color.BLACK;
        return parent.root();

    }

    private RBTreeNode<Type> deleteNode(){
        if()
    }

    private RBTreeNode<Type> root() {
        RBTreeNode<Type> n = this;
        while( n.parent != null ) {
            n = n.parent;
        }
        return n;
    }

    public void balanceInsert() {
        if( color.equals( Color.BLACK ) ) {
            return;
        }

        if( parent == null ) {
            color = Color.BLACK;
            return;
        }
        if( parent.color.equals( Color.BLACK ) ) {
            return;
        }
        RBTreeNode<Type> uncle = uncle();
        RBTreeNode<Type> grandParent = grandParent();

        if( Color.of( uncle ).equals( Color.RED ) ) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandParent.color = Color.RED;
            grandParent.balanceInsert();

        }

        if( this == parent.right && parent == grandParent.left ) {
            parent.rotateLeft();
            left.insertCase5();
        } else if( this == parent.left && parent == grandParent.right ) {
            parent.rotateRight();
            right.insertCase5();
        }

    }

    private void insertCase5() {
        RBTreeNode<Type> grandParent = grandParent();
        parent.color = Color.BLACK;
        grandParent.color = Color.RED;
        if( this == parent.left ) {
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

        if( gp != null ) {
            if( this == gp.left ) {
                gp.left = p;
            } else {
                gp.right = p;
            }
        }

        l.right = lr;
        if( lr != null ) {
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

        if( gp != null ) {
            if( this == gp.left ) {
                gp.left = p;
            } else {
                gp.right = p;
            }
        }
        r.left = rl;
        if( rl != null ) {
            rl.parent = r;
        }

        p.right = r;
        r.parent = p;

        p.parent = gp;
    }

    RBTreeNode<Type> sibling() {
        if( parent.left == this ) {
            return parent.right;
        } else {
            return parent.left;
        }

    }

    RBTreeNode<Type> grandParent() {
        if( parent != null ) {
            return parent.parent;
        }
        return null;
    }

    RBTreeNode<Type> uncle() {
        RBTreeNode<Type> grandParent = grandParent();
        if( grandParent == null ) {
            return null;
        }
        if( parent == grandParent.left ) {
            return grandParent.right;
        } else {
            return grandParent.left;
        }
    }
}
