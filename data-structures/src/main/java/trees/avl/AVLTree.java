package trees.avl;

import common.DataStructureInterface;

/**
 * Created by jiri.peinlich on 11/05/2016.
 */
public class AVLTree<Type extends Comparable<Type>> implements DataStructureInterface<Type>
{

    private AVLTreeNode<Type> root;

    @Override public boolean contains( Type value ) {
        return root != null && root.contains( value );
    }

    @Override public DataStructureInterface<Type> add( Type value ) {
        if( root != null ) {
            root = root.add( value );
        } else {
            root = new AVLTreeNode<>( value, null, null );
        }
        return this;
    }

    @Override public DataStructureInterface<Type> remove( Type value ) {
        if( root != null ) {
            root = root.remove( value );
        }
        return this;
    }

    public static class Factory<Type extends Comparable<Type>> implements DataStructureInterface.Factory<Type>
    {

        @Override public DataStructureInterface<Type> returnEmptyInstance() {
            return new AVLTree<>();
        }
    }

}
