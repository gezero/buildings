package knight;

import math.Coordinate;

/**
 * Created by jiri.peinlich on 20/01/2016.
 */
public enum Moves {
    ONE {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() + 1, oldCoordinate.getY() + 2);
        }
    }, TWO {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() + 2, oldCoordinate.getY() + 1);
        }
    }, THREE {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() + 1, oldCoordinate.getY() - 2);
        }
    }, FOUR {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() + 2, oldCoordinate.getY() - 1);
        }
    }, FIVE {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() - 1, oldCoordinate.getY() + 2);
        }
    }, SIX {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() - 2, oldCoordinate.getY() + 1);
        }
    }, SEVEN {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() - 1, oldCoordinate.getY() - 2);
        }
    }, EIGHT {
        @Override
        Coordinate newCoordinate(Coordinate oldCoordinate) {
            return new Coordinate(oldCoordinate.getX() - 2, oldCoordinate.getY() - 1);
        }
    };

    abstract Coordinate newCoordinate(Coordinate oldCoordinate);
}
