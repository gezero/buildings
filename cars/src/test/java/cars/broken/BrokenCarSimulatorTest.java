package cars.broken;

import cars.Track;
import math.Coordinate;
import org.junit.Test;

import java.util.Arrays;

import static cars.Track.TrackElement.*;
import static cars.broken.BrokenCarSimulator.Command.STRAIGHT;
import static cars.broken.BrokenCarSimulator.Command.TURN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jiri.peinlich on 20/01/2016.
 */
public class BrokenCarSimulatorTest {

    Track.TrackElement[][] track1 =
            {
                    {START, EMPTY, OBSTACLE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, OBSTACLE, OBSTACLE, EMPTY, EMPTY, EMPTY, OBSTACLE},
                    {EMPTY, EMPTY, EMPTY, EMPTY, OBSTACLE, EMPTY, EMPTY, OBSTACLE},
                    {EMPTY, EMPTY, EMPTY, OBSTACLE, OBSTACLE, EMPTY, EMPTY, EMPTY},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, OBSTACLE, EMPTY, EMPTY},
                    {OBSTACLE, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                    {EMPTY, OBSTACLE, EMPTY, EMPTY, EMPTY, OBSTACLE, OBSTACLE, OBSTACLE},
                    {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, FINISH}
            };
    private Coordinate track1Start = new Coordinate(0, 0);


    @Test
    public void testTrackOne() {
        Track track = new Track(track1);
        BrokenCarSimulator simulator = new BrokenCarSimulator(track, track1Start);


        Track.TrackElement finish = simulator.go(Arrays.asList(STRAIGHT, STRAIGHT, STRAIGHT, STRAIGHT, TURN, STRAIGHT,
                TURN, TURN, TURN, STRAIGHT, TURN, STRAIGHT, STRAIGHT, TURN,
                TURN, TURN, STRAIGHT, STRAIGHT, TURN, STRAIGHT, STRAIGHT, STRAIGHT));

        assertThat(finish, is(Track.TrackElement.FINISH));

    }

}
