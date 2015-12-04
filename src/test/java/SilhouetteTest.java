import org.junit.Before;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by jiri.peinlich on 04/12/2015.
 */
public class SilhouetteTest {

    Silhouette silhouette;
    private List<Building> buildings;

    @Before
    public void setUp() throws Exception {
        buildings = new ArrayList<Building>();

        //TODO: put here your class;
        silhouette = new Solution();
    }

    @org.junit.Test
    public void testCalculateSilhouetteNoBuildings() throws Exception {

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);
        assertThat(coordinates.size(),is(0));
    }
    

    @org.junit.Test
    public void testCalculateSilhouetteOneHouse() throws Exception {
        buildings.add(new Building(2, 3, 10));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(3, 10), Coordinate.of(3, 0)));
    }

    @org.junit.Test
    public void testCalculateSilhouetteTwoHouses() throws Exception {
        buildings.add(new Building(2, 3, 10));
        buildings.add(new Building(4, 5, 2));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(3, 10), Coordinate.of(3, 0),
                Coordinate.of(4, 0), Coordinate.of(4, 2), Coordinate.of(5, 2), Coordinate.of(5, 0)));
    }


    @org.junit.Test
    public void testCalculateSilhouetteTwoOverLappingBuildings() throws Exception {
        buildings.add(new Building(2, 6, 10));
        buildings.add(new Building(4, 7, 2));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(6, 10),
                Coordinate.of(7, 2), Coordinate.of(7, 0)));
    }

    @org.junit.Test
    public void testCalculateSilhouetteALotOfBuildings() throws Exception {
        Random generator = new Random(0);

        int pos =0;
        for (int i = 0; i < 1000; i++) {
            pos += generator.nextInt(5);
            int left = pos + generator.nextInt(5);
            int right = left+1 + generator.nextInt(20);
            int height = generator.nextInt(20);
            buildings.add(new Building(left,right,height));
        }


        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        //todo:Add proper check, hard to say yet what it is.
        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(6, 10),
                Coordinate.of(7, 2), Coordinate.of(7, 0)));
    }
}
