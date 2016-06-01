package avl;

import org.junit.Ignore;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jiri.peinlich on 11/05/2016.
 */
public class AVLTreeTest
{

/*
    public static final String LOADS_OF_INTEGERS_FILENAME = "target/loads_of_integers_filename";
    public static final String SORTED_LOADS_OF_INTEGERS_FILENAME = "target/sorted_loads_of_integers_filename";

    @Test
    public void singleElementAddRemoveContains() throws Exception {
        AVLTree<Integer> emptyTree = AVLTree.createEmptyTree();
        assertThat( emptyTree.contains( 10 ), is( false ) );
        emptyTree.add( 10 );
        assertThat( emptyTree.contains( 10 ), is( true ) );
        emptyTree.remove( 10 );
        assertThat( emptyTree.contains( 10 ), is( false ) );
    }

    @Test
    @Ignore
    public void generateLoadsOfData() throws Exception {

        int amount = 100000;
        int max = 10000000;

        Random r = new Random();

        List<Integer> integers = new ArrayList<>();

        try (PrintWriter w = new PrintWriter( LOADS_OF_INTEGERS_FILENAME )) {
            for( int i = 0; i < amount; i++ ) {
                int x = r.nextInt( max );
                integers.add( x );
                w.println( x );
            }
        }

        integers.sort( Integer::compareTo );

        try (PrintWriter w = new PrintWriter( SORTED_LOADS_OF_INTEGERS_FILENAME )) {
            integers.forEach( integer -> w.println(integer) );
        }

    }
*/

}
