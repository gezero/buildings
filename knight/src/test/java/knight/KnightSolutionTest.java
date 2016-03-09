package knight;

import math.Coordinate;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiri.peinlich on 20/01/2016.
 */
public class KnightSolutionTest {

    private static final Logger logger = LoggerFactory.getLogger(KnightSolutionTest.class);
    private Board board = new Board(6);

    @Before
    public void placeKnight() {
        board.set(new Coordinate(0, 0), 1);
    }

    @Test
    public void test() {
        KnightSolution solution = new KnightSolution();
        boolean solve = solution.solve(board);
        if (solve) {

            logger.info(board.toString());
        } else {
            logger.info("I am so sorry!");
        }
    }


}
