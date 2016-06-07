package lists;

import common.DataStructureInterface;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by jiri.peinlich on 31/05/2016.
 */
public class ReferenceArrayList <Type extends Comparable<Type>> implements DataStructureInterface<Type>
{
    ArrayList<Type> arrayList = new ArrayList<>(  );


    @Override public boolean contains( Type value ) {
        return arrayList.contains( value );
    }

    @Override public DataStructureInterface<Type> add( Type value ) {
        arrayList.add( value );
        return this;
    }

    @Override public DataStructureInterface<Type> remove( Type value ) {
        arrayList.removeAll( Collections.singleton(value));
        return this;
    }

    public static class Factory<Type extends Comparable<Type>> implements DataStructureInterface.Factory<Type>{

        @Override public DataStructureInterface<Type> returnEmptyInstance() {
            return new ReferenceArrayList<>();
        }
    }
}
