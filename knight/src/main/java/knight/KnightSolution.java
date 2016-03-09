package knight;

import math.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jiri.peinlich on 20/01/2016.
 */
public class KnightSolution {

    private static final Logger logger = LoggerFactory.getLogger(KnightSolution.class);

    int count = 0;

    public boolean solve(Board board) {

        int round = 1;
        Coordinate knightPosition = board.find(round);
        boolean result = nextStep(round, knightPosition, board);
        logger.info("Used steps: {}", count);
        return result;


    }

    private boolean nextStep(int depth, Coordinate knightPosition, Board board) {
        int deeperDepth = depth + 1;
        count++;

        for (Moves move : Moves.values()) {
            Coordinate coordinate = move.newCoordinate(knightPosition);
            Integer value = board.get(coordinate);
            if (value != null && value == 0) { //we can move here
                board.set(coordinate, deeperDepth);
                boolean solved = nextStep(deeperDepth, coordinate, board);
                if (solved) {
                    return true;
                }
                board.set(coordinate, 0);
            }
            if (depth == board.getSize() * board.getSize()) {
                if (value != null && value == 1) {//we found the solution
                    return true;
                } else {
                    logger.info("Full board:" + board);
                    return false;
                }
            }
        }
        return false;
    }

}
