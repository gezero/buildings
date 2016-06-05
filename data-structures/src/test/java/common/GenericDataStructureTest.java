package common;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jiri.peinlich on 31/05/2016.
 */
public abstract class GenericDataStructureTest
{
    private static final int LIMIT = 1000;
    private static final int SEED =0;
    Random random = new Random( SEED );
    private DataStructureInterface.Factory<Integer> factory;

    protected abstract DataStructureInterface.Factory<Integer> getDataStructureFactory();

    @Before
    public void findFactory() {
        this.factory = getDataStructureFactory();
    }

    @Test
    public void singleElementAddRemoveContains() throws Exception {
        DataStructureInterface<Integer> ds = factory.returnEmptyInstance();
        assertThat( ds.contains( 10 ), is( false ) );
        ds.add( 10 );
        assertThat( ds.contains( 10 ), is( true ) );
        ds.remove( 10 );
        assertThat( ds.contains( 10 ), is( false ) );
    }

    @Test
    public void fewElements() throws Exception {

        DataStructureInterface<Integer> root = factory.returnEmptyInstance();
        DataStructureInterface<Integer> newRoot = root
                .add( 10 )
                .add( 50 )
                .add( 17 )
                .add( 22 )
                .add( 30 )
                .add( 2 )
                .add( 15 );

        assertThat( newRoot.contains( 50 ), is( true ) );
        assertThat( newRoot.contains( 17 ), is( true ) );
        assertThat( newRoot.contains( 22 ), is( true ) );
        assertThat( newRoot.contains( 30 ), is( true ) );
        assertThat( newRoot.contains( 2 ), is( true ) );
        assertThat( newRoot.contains( 15 ), is( true ) );

    }

    @Test
    public void fewDeletes() throws Exception {

        DataStructureInterface<Integer> root = factory.returnEmptyInstance();
        DataStructureInterface<Integer> newRoot = root
                .add( 10 )
                .add( 50 )
                .add( 17 )
                .add( 22 )
                .add( 30 )
                .remove( 17 )
                .add( 2 )
                .add( 15 )
                .remove( 1000 );

        assertThat( newRoot.contains( 50 ), is( true ) );
        assertThat( newRoot.contains( 17 ), is( false ) );
        assertThat( newRoot.contains( 22 ), is( true ) );
        assertThat( newRoot.contains( 30 ), is( true ) );
        assertThat( newRoot.contains( 2 ), is( true ) );
        assertThat( newRoot.contains( 15 ), is( true ) );

    }

    @Test
    public void loadsOfOrderedElementsTest() {
        long start = System.nanoTime();
        DataStructureInterface<Integer> ds = factory.returnEmptyInstance();


        for( int i = 0; i < LIMIT; i++ ) {
            ds = ds.add( i );
        }
        long insert = System.nanoTime();

        long contains = containsAllInts( ds );

        HashSet<Integer> set = new HashSet<>( LIMIT );
        int counter = removeRandomElements( ds, set );

        long delete = System.nanoTime();

        long end = containsButSet( ds, set );

        printResults( start, insert, contains, LIMIT - counter, delete, end );
    }


    @Test
    public void loadsOfRandomElementsTest() {
        long start = System.nanoTime();
        DataStructureInterface<Integer> ds = factory.returnEmptyInstance();

        int in =0;
        HashSet<Integer> setIn = new HashSet<>( LIMIT );

        for( int i = 0; i < LIMIT; i++ ) {
            int value = random.nextInt( LIMIT );
            setIn.add(value);
            ds = ds.add( value );
        }
        long insert = System.nanoTime();

        long contains = containsSet( ds,setIn );


        HashSet<Integer> set = new HashSet<>( LIMIT );
        int out = removeRandomElements( ds, set );

        long delete = System.nanoTime();

        long end = containsSetButSet( ds, setIn, set );

        printResults( start, insert, contains, in - out, delete, end );
    }

    private int removeRandomElements( DataStructureInterface<Integer> ds, HashSet<Integer> set ) {
        int counter = 0;
        for( int i = 0; i < LIMIT; i++ ) {
            int v = random.nextInt(LIMIT);
            if (set.contains( v )){
                ++counter;
            } else {
                set.add( v );
                ds.remove( v );
            }
        }
        return counter;
    }

    private void printResults( long start, long insert, long contains, int counter, long delete, long end ) {
        System.out.println( "Insert in test: " + getClass().getName() + ".loadsOfOrderedElementsTest took: " + String
                .valueOf( insert - start ) );
        System.out.println( "Contains in test: " + getClass().getName() + ".loadsOfOrderedElementsTest took: " + String
                .valueOf( contains - insert ) );
        System.out.println( "Delete in test: " + getClass().getName() + ".loadsOfOrderedElementsTest took: " + String
                .valueOf( delete - contains ) );
        System.out.println( "Contains in test: " + getClass().getName() + ".loadsOfOrderedElementsTest took: " + String
                .valueOf( end - delete ) +" ("+counter+" items remaining)");
    }

    private long containsSet( DataStructureInterface<Integer> ds, HashSet<Integer> set ) {
        for( int i = 0; i < LIMIT; i++ ) {
            boolean contains = ds.contains( i );
            boolean contains1 = set.contains( i );
            if ( contains != contains1 ){
                assertThat( contains, is( contains1 ) );
            }
        }
        return System.nanoTime();
    }
    private long containsButSet( DataStructureInterface<Integer> ds, HashSet<Integer> set ) {
        for( int i = 0; i < LIMIT; i++ ) {
            boolean contains = ds.contains( i );
            boolean contains1 = set.contains( i );
            if ( contains == contains1 ){
                assertThat( contains, is( !contains1 ) );
            }
        }
        return System.nanoTime();
    }

    private long containsSetButSet( DataStructureInterface<Integer> ds, HashSet<Integer> setIn , HashSet<Integer> set ) {

        for( Integer i : setIn ) {
            boolean contains = ds.contains( i );
            boolean contains1 = set.contains( i );
            if ( contains == contains1 ){
                assertThat( contains, is( !contains1 ) );
            }
        }
        return System.nanoTime();
    }

    private long containsAllInts( DataStructureInterface<Integer> ds ) {
        for( int i = 0; i < LIMIT; i++ ) {
            assertThat( ds.contains( random.nextInt(LIMIT) ), is( true ) );
        }
        return System.nanoTime();
    }

}
