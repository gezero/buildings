package cars.broken;

import cars.CarsException;
import cars.Track;
import math.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Broken has a direction, and it can either move one step straight, or it can turn 90 degrees to the right and then
 * move one step straight. When it reaches an obstacle it crashes.
 */

public class BrokenCarSimulator {

    private static final Logger logger = LoggerFactory.getLogger(BrokenCarSimulator.class);

    private final BrokenCar car;
    private final Track track;


    public BrokenCarSimulator(Track track, Coordinate startLocation) {
        this.track = track;
        if (!(track.element(startLocation) == Track.TrackElement.START)) {
            throw new CarsException("Wrong start");
        }
        this.car = new BrokenCar(Direction.EAST, startLocation);
    }

    public Track.TrackElement step(Command command) {
        if (command.equals(Command.TURN)) {
            car.setDirection(car.getDirection().nextDirection());
        }
        move();
        return track.element(car.getLocation());
    }

    public Track.TrackElement go(List<Command> commands) {
        for (Command command : commands) {
            step(command);
        }
        return track.element(car.getLocation());
    }

    private void move() {
        Coordinate newLocation = car.getDirection().neighbor(car.getLocation());
        if (track.element(newLocation).equals(Track.TrackElement.OBSTACLE)) {
            throw new CarsException("Crashed on position " + newLocation);
        }
        logger.info("Moving car to location " + newLocation);
        car.setLocation(newLocation);
    }

    public enum Command {
        /**
         * Car will move one step towards the direction it is facing
         */
        STRAIGHT,
        /**
         * Car turn to the right and move one step towards the new direction it will be facing
         */
        TURN
    }

    public enum Direction {
        NORTH {
            @Override
            Direction nextDirection() {
                return EAST;
            }

            @Override
            Coordinate neighbor(Coordinate coordinate) {
                return new Coordinate(coordinate.getX(), coordinate.getY() - 1);
            }
        },
        EAST {
            @Override
            Direction nextDirection() {
                return SOUTH;
            }

            @Override
            Coordinate neighbor(Coordinate coordinate) {
                return new Coordinate(coordinate.getX() + 1, coordinate.getY());
            }
        },
        SOUTH {
            @Override
            Direction nextDirection() {
                return WEST;
            }

            @Override
            Coordinate neighbor(Coordinate coordinate) {
                return new Coordinate(coordinate.getX(), coordinate.getY() + 1);
            }

        },
        WEST {
            @Override
            Direction nextDirection() {
                return NORTH;
            }

            @Override
            Coordinate neighbor(Coordinate coordinate) {
                return new Coordinate(coordinate.getX() - 1, coordinate.getY());
            }

        };

        abstract Direction nextDirection();

        abstract Coordinate neighbor(Coordinate coordinate);
    }

    public class BrokenCar {
        private Direction direction;
        private Coordinate location;

        public BrokenCar(Direction direction, Coordinate location) {
            this.direction = direction;
            this.location = location;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public Coordinate getLocation() {
            return location;
        }

        public void setLocation(Coordinate location) {
            this.location = location;
        }
    }

}
