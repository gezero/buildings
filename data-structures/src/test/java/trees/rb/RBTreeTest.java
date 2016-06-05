package trees.rb;

import common.DataStructureInterface;
import common.GenericDataStructureTest;

/**
 * @author Jiri
 */
public class RBTreeTest extends GenericDataStructureTest {

    @Override
    protected DataStructureInterface.Factory<Integer> getDataStructureFactory() {
        return new RBTree.Factory<>();
    }
}