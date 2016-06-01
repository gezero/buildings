package lists;

import common.DataStructureInterface;
import common.GenericDataStructureTest;

import static org.junit.Assert.*;

/**
 * Created by jiri.peinlich on 31/05/2016.
 */
public class ReferenceArrayListTest extends GenericDataStructureTest
{

    @Override protected DataStructureInterface.Factory<Integer> getDataStructureFactory() {
        return new ReferenceArrayList.Factory<>();
    }
}
