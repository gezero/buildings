package trees.avl;

/**
 * Created by jiri.peinlich on 31/05/2016.
 */
public class AVLTreeNode <Type extends Comparable<Type>>{

    private final Type value;
    private final AVLTreeNode<Type> left;
    private final AVLTreeNode<Type> right;
    private final MetaData metaData;

    public AVLTreeNode( Type value, AVLTreeNode<Type> left, AVLTreeNode<Type> right ) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.metaData = new MetaData( left != null ? left.metaData.max() + 1 : 0,
                right != null ? right.metaData.max() + 1 : 0 );
    }

    public AVLTreeNode<Type> add( Type v ) {
        AVLTreeNode<Type> newLeft = left;
        AVLTreeNode<Type> newRight = right;
        if( v.compareTo( value ) > 0 ) {  //v > value
            if( right != null ) {
                newRight = right.add( v );
            } else {
                newRight = new AVLTreeNode<>( v, null, null );
            }
        } else if( v.compareTo( value ) < 0 ) {  // v < value
            if( left != null ) {
                newLeft = left.add( v );
            } else {
                newLeft = new AVLTreeNode<>( v, null, null );
            }
        } else {  // v == value
            return this;
        }
        AVLTreeNode<Type> result = new AVLTreeNode<>( value, newLeft, newRight );
        int factor = result.balance();
        if( factor == -2 ) {
            if( result.left.metaData.balance() == -1 ) {
                return result.rotateRight();
            } else {
                return new AVLTreeNode<>( result.value, result.left.rotateLeft(), result.right ).rotateRight();
            }
        } else if( factor == 2 ) {
            if( result.right.metaData.balance() == 1 ) {
                return result.rotateLeft();
            } else {
                return new AVLTreeNode<>( result.value, result.left, result.right.rotateRight() ).rotateLeft();
            }

        }
        return result;
    }

    private int balance() {
        return metaData.balance();
    }

    private AVLTreeNode<Type> rotateLeft() {
        AVLTreeNode<Type> newLeft = new AVLTreeNode<>( value, left, right.left );
        return new AVLTreeNode<>( right.value, newLeft, right.right );
    }

    private AVLTreeNode<Type> rotateRight() {
        AVLTreeNode<Type> newRight = new AVLTreeNode<>( value, left.right, right );
        return new AVLTreeNode<>( left.value, left.left, newRight );
    }

    public AVLTreeNode<Type> remove( Type v ) {

        AVLTreeNode<Type> newLeft = left;
        AVLTreeNode<Type> newRight = right;
        Type newValue = value;

        if( v.compareTo( value ) > 0 ) {   //v > value
            if( right != null ) {
                newRight = right.remove( v );
            }
        } else if( v.compareTo( value ) < 0 ) {  //v < value
            if( left != null ) {
                newLeft = left.remove( v );
            }
        } else { // v == value
            if( left == null ) {
                return right;
            }
            if( right == null ) {
                return left;
            }

            newValue = left.maxValue();
            newLeft = left.remove( newValue );

        }
        return new AVLTreeNode<>( newValue, newLeft, newRight );
    }

    private Type maxValue() {
        if( right == null ) {
            return value;
        }
        return right.maxValue();
    }

    public boolean contains( Type v ) {
        if( v.compareTo( value ) > 0 ) { //v > value
            return right != null && right.contains( v );
        }
        // v < value
        return v.compareTo( value ) >= 0 || left != null && left.contains( v );

    }

    private class MetaData
    {
        final int leftDepth;
        final int rightDepth;

        public MetaData( int leftDepth, int rightDepth ) {
            this.leftDepth = leftDepth;
            this.rightDepth = rightDepth;
        }

        public int max() {
            return Math.max( leftDepth, rightDepth );
        }

        public int balance() {
            return rightDepth - leftDepth;
        }

    }

}
