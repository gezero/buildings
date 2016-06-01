package common;

/**
 * Created by jiri.peinlich on 31/05/2016.
 */
public interface DataStructureInterface<Type>
{
    /**
     * Should return true if and only if the data structure contains the value.
     */
    boolean contains( Type value );

    /**
     * Adds value to the data structure.
     *
     * @param value value to be inserted
     * @return data structure after the new element was inserted
     */
    DataStructureInterface<Type> add( Type value );

    /**
     * Inserts all the values from input into the data structure.
     */
    default DataStructureInterface<Type>  addAll( Iterable<Type> values ) {
        DataStructureInterface<Type> result = this;
        for( Type value : values ) {
            result = result.add( value );
        }
        return result;
    }

    /**
     *  Removes all instances of the input value element from the data structure in case contains(value) is true.
     *  Does nothing in case contains(value) is false.
     */
    DataStructureInterface<Type> remove(Type value);

    /**
     * Removes all values from the data structure
     */
    default DataStructureInterface<Type> removeAll(Iterable<Type> values){
        DataStructureInterface<Type> result = this;
        for( Type value : values ) {
            result = result.remove( value );
        }
        return result;
    }


    public interface Factory<Type extends Comparable<Type>>{
        /**
         * @return an empty instance of the data structure so that the other operations can be executed on it.
         */
        DataStructureInterface<Type> returnEmptyInstance();
    }


}
