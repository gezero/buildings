package buildings;

import math.Coordinate;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
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
        assertThat(coordinates.size(), is(0));
    }


    @org.junit.Test
    public void testCalculateSilhouetteOneHouse() throws Exception {
        buildings.add(new Building(2, 3, 10));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(3, 10), Coordinate
                .of(3, 0)));
    }

    @org.junit.Test
    public void testCalculateSilhouetteTwoHouses() throws Exception {
        buildings.add(new Building(2, 3, 10));
        buildings.add(new Building(4, 5, 2));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(3, 10), Coordinate
                        .of(3, 0),
                Coordinate.of(4, 0), Coordinate.of(4, 2), Coordinate.of(5, 2), Coordinate.of(5, 0)));
    }


    @org.junit.Test
    public void testCalculateSilhouetteTwoOverLappingBuildingsSecondIsSmaller() throws Exception {
        buildings.add(new Building(2, 6, 10));
        buildings.add(new Building(4, 7, 2));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(6, 10),
                Coordinate.of(6, 2), Coordinate.of(7, 2), Coordinate.of(7, 0)));
    }

    @org.junit.Test
    public void testCalculateSilhouetteTwoOverLappingBuildingsSecondIsBigger() throws Exception {
        buildings.add(new Building(2, 6, 10));
        buildings.add(new Building(4, 7, 12));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(4, 10),
                Coordinate.of(4, 12), Coordinate.of(7, 12), Coordinate.of(7, 0)));
    }


    @org.junit.Test
    public void testCalculateSilhouetteTwoOverLappingBuildingsSameStartHighestFirst() throws Exception {
        buildings.add(new Building(2, 6, 10));
        buildings.add(new Building(2, 7, 2));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(6, 10),
                Coordinate.of(6, 2), Coordinate.of(7, 2), Coordinate.of(7, 0)));
    }

    @org.junit.Test
    public void testCalculateSilhouetteTwoOverLappingBuildingsSameStartSmalestFirst() throws Exception {
        buildings.add(new Building(2, 7, 2));
        buildings.add(new Building(2, 6, 10));

        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);

        assertThat(coordinates, contains(Coordinate.of(2, 0), Coordinate.of(2, 10), Coordinate.of(6, 10),
                Coordinate.of(6, 2), Coordinate.of(7, 2), Coordinate.of(7, 0)));
    }

    @org.junit.Test
    public void testCalculateSilhouetteALotOfBuildings() throws Exception {
        Random generator = new Random(0);

        int pos = 0;
        for (int i = 0; i < 1000; i++) {
            pos += generator.nextInt(5);
            int left = pos + generator.nextInt(5);
            int right = left + 1 + generator.nextInt(20);
            int height = 1 + generator.nextInt(20);
            buildings.add(new Building(left, right, height));
        }


        List<Coordinate> coordinates = silhouette.calculateSilhouette(buildings);
//        int i=1;
//        for (buildings.Coordinate coordinate : coordinates) {
//            System.out.print("buildings.Coordinate.of(" + coordinate.getX() + "," + coordinate.getY() + "),");
//            if ( (i++) %4 ==0){
//                System.out.println();
//            }
//        }


        //todo:Add proper check, hard to say yet what it is.
        assertThat(coordinates, contains(
                Coordinate.of(3, 0), Coordinate.of(3, 8), Coordinate.of(8, 8), Coordinate.of(8, 18),
                Coordinate.of(26, 18), Coordinate.of(26, 17), Coordinate.of(27, 17), Coordinate.of(27, 18),
                Coordinate.of(34, 18), Coordinate.of(34, 19), Coordinate.of(48, 19), Coordinate.of(48, 13),
                Coordinate.of(50, 13), Coordinate.of(50, 14), Coordinate.of(54, 14), Coordinate.of(54, 18),
                Coordinate.of(69, 18), Coordinate.of(69, 20), Coordinate.of(84, 20), Coordinate.of(84, 19),
                Coordinate.of(95, 19), Coordinate.of(95, 17), Coordinate.of(96, 17), Coordinate.of(96, 19),
                Coordinate.of(111, 19), Coordinate.of(111, 15), Coordinate.of(115, 15), Coordinate.of(115, 14),
                Coordinate.of(121, 14), Coordinate.of(121, 11), Coordinate.of(123, 11), Coordinate.of(123, 17),
                Coordinate.of(126, 17), Coordinate.of(126, 18), Coordinate.of(146, 18), Coordinate.of(146, 19),
                Coordinate.of(147, 19), Coordinate.of(147, 18), Coordinate.of(149, 18), Coordinate.of(149, 15),
                Coordinate.of(160, 15), Coordinate.of(160, 12), Coordinate.of(162, 12), Coordinate.of(162, 17),
                Coordinate.of(181, 17), Coordinate.of(181, 15), Coordinate.of(184, 15), Coordinate.of(184, 18),
                Coordinate.of(187, 18), Coordinate.of(187, 15), Coordinate.of(188, 15), Coordinate.of(188, 20),
                Coordinate.of(201, 20), Coordinate.of(201, 18), Coordinate.of(210, 18), Coordinate.of(210, 20),
                Coordinate.of(219, 20), Coordinate.of(219, 19), Coordinate.of(222, 19), Coordinate.of(222, 16),
                Coordinate.of(223, 16), Coordinate.of(223, 17), Coordinate.of(237, 17), Coordinate.of(237, 18),
                Coordinate.of(256, 18), Coordinate.of(256, 10), Coordinate.of(260, 10), Coordinate.of(260, 8),
                Coordinate.of(261, 8), Coordinate.of(261, 16), Coordinate.of(272, 16), Coordinate.of(272, 17),
                Coordinate.of(276, 17), Coordinate.of(276, 18), Coordinate.of(280, 18), Coordinate.of(280, 17),
                Coordinate.of(291, 17), Coordinate.of(291, 15), Coordinate.of(297, 15), Coordinate.of(297, 13),
                Coordinate.of(304, 13), Coordinate.of(304, 15), Coordinate.of(305, 15), Coordinate.of(305, 19),
                Coordinate.of(308, 19), Coordinate.of(308, 20), Coordinate.of(317, 20), Coordinate.of(317, 19),
                Coordinate.of(321, 19), Coordinate.of(321, 18), Coordinate.of(322, 18), Coordinate.of(322, 19),
                Coordinate.of(327, 19), Coordinate.of(327, 20), Coordinate.of(333, 20), Coordinate.of(333, 17),
                Coordinate.of(339, 17), Coordinate.of(339, 14), Coordinate.of(342, 14), Coordinate.of(342, 19),
                Coordinate.of(345, 19), Coordinate.of(345, 14), Coordinate.of(349, 14), Coordinate.of(349, 19),
                Coordinate.of(358, 19), Coordinate.of(358, 18), Coordinate.of(365, 18), Coordinate.of(365, 14),
                Coordinate.of(367, 14), Coordinate.of(367, 20), Coordinate.of(374, 20), Coordinate.of(374, 19),
                Coordinate.of(386, 19), Coordinate.of(386, 11), Coordinate.of(388, 11), Coordinate.of(388, 17),
                Coordinate.of(395, 17), Coordinate.of(395, 15), Coordinate.of(410, 15), Coordinate.of(410, 14),
                Coordinate.of(411, 14), Coordinate.of(411, 18), Coordinate.of(420, 18), Coordinate.of(420, 17),
                Coordinate.of(422, 17), Coordinate.of(422, 19), Coordinate.of(427, 19), Coordinate.of(427, 20),
                Coordinate.of(441, 20), Coordinate.of(441, 18), Coordinate.of(460, 18), Coordinate.of(460, 19),
                Coordinate.of(471, 19), Coordinate.of(471, 18), Coordinate.of(498, 18), Coordinate.of(498, 16),
                Coordinate.of(509, 16), Coordinate.of(509, 12), Coordinate.of(519, 12), Coordinate.of(519, 11),
                Coordinate.of(528, 11), Coordinate.of(528, 10), Coordinate.of(535, 10), Coordinate.of(535, 12),
                Coordinate.of(538, 12), Coordinate.of(538, 15), Coordinate.of(549, 15), Coordinate.of(549, 13),
                Coordinate.of(550, 13), Coordinate.of(550, 16), Coordinate.of(559, 16), Coordinate.of(559, 18),
                Coordinate.of(585, 18), Coordinate.of(585, 20), Coordinate.of(603, 20), Coordinate.of(603, 17),
                Coordinate.of(608, 17), Coordinate.of(608, 20), Coordinate.of(625, 20), Coordinate.of(625, 19),
                Coordinate.of(634, 19), Coordinate.of(634, 14), Coordinate.of(651, 14), Coordinate.of(651, 6),
                Coordinate.of(653, 6), Coordinate.of(653, 8), Coordinate.of(660, 8), Coordinate.of(660, 18),
                Coordinate.of(669, 18), Coordinate.of(669, 15), Coordinate.of(675, 15), Coordinate.of(675, 19),
                Coordinate.of(681, 19), Coordinate.of(681, 17), Coordinate.of(683, 17), Coordinate.of(683, 20),
                Coordinate.of(703, 20), Coordinate.of(703, 19), Coordinate.of(717, 19), Coordinate.of(717, 15),
                Coordinate.of(726, 15), Coordinate.of(726, 20), Coordinate.of(734, 20), Coordinate.of(734, 18),
                Coordinate.of(737, 18), Coordinate.of(737, 20), Coordinate.of(745, 20), Coordinate.of(745, 15),
                Coordinate.of(747, 15), Coordinate.of(747, 12), Coordinate.of(761, 12), Coordinate.of(761, 17),
                Coordinate.of(762, 17), Coordinate.of(762, 18), Coordinate.of(765, 18), Coordinate.of(765, 17),
                Coordinate.of(768, 17), Coordinate.of(768, 16), Coordinate.of(769, 16), Coordinate.of(769, 19),
                Coordinate.of(794, 19), Coordinate.of(794, 17), Coordinate.of(807, 17), Coordinate.of(807, 18),
                Coordinate.of(824, 18), Coordinate.of(824, 19), Coordinate.of(842, 19), Coordinate.of(842, 17),
                Coordinate.of(853, 17), Coordinate.of(853, 19), Coordinate.of(854, 19), Coordinate.of(854, 17),
                Coordinate.of(859, 17), Coordinate.of(859, 9), Coordinate.of(860, 9), Coordinate.of(860, 14),
                Coordinate.of(862, 14), Coordinate.of(862, 18), Coordinate.of(868, 18), Coordinate.of(868, 19),
                Coordinate.of(872, 19), Coordinate.of(872, 20), Coordinate.of(881, 20), Coordinate.of(881, 19),
                Coordinate.of(886, 19), Coordinate.of(886, 18), Coordinate.of(893, 18), Coordinate.of(893, 19),
                Coordinate.of(897, 19), Coordinate.of(897, 11), Coordinate.of(899, 11), Coordinate.of(899, 18),
                Coordinate.of(922, 18), Coordinate.of(922, 17), Coordinate.of(944, 17), Coordinate.of(944, 11),
                Coordinate.of(950, 11), Coordinate.of(950, 15), Coordinate.of(951, 15), Coordinate.of(951, 17),
                Coordinate.of(956, 17), Coordinate.of(956, 18), Coordinate.of(968, 18), Coordinate.of(968, 19),
                Coordinate.of(983, 19), Coordinate.of(983, 18), Coordinate.of(990, 18), Coordinate.of(990, 15),
                Coordinate.of(1004, 15), Coordinate.of(1004, 20), Coordinate.of(1010, 20), Coordinate.of(1010, 19),
                Coordinate.of(1011, 19), Coordinate.of(1011, 20), Coordinate.of(1023, 20), Coordinate.of(1023, 14),
                Coordinate.of(1024, 14), Coordinate.of(1024, 17), Coordinate.of(1027, 17), Coordinate.of(1027, 19),
                Coordinate.of(1029, 19), Coordinate.of(1029, 17), Coordinate.of(1030, 17), Coordinate.of(1030, 18),
                Coordinate.of(1034, 18), Coordinate.of(1034, 20), Coordinate.of(1042, 20), Coordinate.of(1042, 18),
                Coordinate.of(1046, 18), Coordinate.of(1046, 13), Coordinate.of(1047, 13), Coordinate.of(1047, 16),
                Coordinate.of(1051, 16), Coordinate.of(1051, 10), Coordinate.of(1061, 10), Coordinate.of(1061, 9),
                Coordinate.of(1062, 9), Coordinate.of(1062, 19), Coordinate.of(1080, 19), Coordinate.of(1080, 18),
                Coordinate.of(1084, 18), Coordinate.of(1084, 20), Coordinate.of(1089, 20), Coordinate.of(1089, 18),
                Coordinate.of(1091, 18), Coordinate.of(1091, 16), Coordinate.of(1094, 16), Coordinate.of(1094, 17),
                Coordinate.of(1098, 17), Coordinate.of(1098, 20), Coordinate.of(1113, 20), Coordinate.of(1113, 18),
                Coordinate.of(1129, 18), Coordinate.of(1129, 19), Coordinate.of(1149, 19), Coordinate.of(1149, 8),
                Coordinate.of(1150, 8), Coordinate.of(1150, 11), Coordinate.of(1158, 11), Coordinate.of(1158, 18),
                Coordinate.of(1169, 18), Coordinate.of(1169, 15), Coordinate.of(1170, 15), Coordinate.of(1170, 19),
                Coordinate.of(1176, 19), Coordinate.of(1176, 20), Coordinate.of(1196, 20), Coordinate.of(1196, 19),
                Coordinate.of(1205, 19), Coordinate.of(1205, 16), Coordinate.of(1212, 16), Coordinate.of(1212, 10),
                Coordinate.of(1214, 10), Coordinate.of(1214, 20), Coordinate.of(1215, 20), Coordinate.of(1215, 12),
                Coordinate.of(1229, 12), Coordinate.of(1229, 10), Coordinate.of(1230, 10), Coordinate.of(1230, 16),
                Coordinate.of(1247, 16), Coordinate.of(1247, 12), Coordinate.of(1252, 12), Coordinate.of(1252, 15),
                Coordinate.of(1264, 15), Coordinate.of(1264, 6), Coordinate.of(1265, 6), Coordinate.of(1265, 0),
                Coordinate.of(1268, 0), Coordinate.of(1268, 1), Coordinate.of(1271, 1), Coordinate.of(1271, 18),
                Coordinate.of(1273, 18), Coordinate.of(1273, 19), Coordinate.of(1286, 19), Coordinate.of(1286, 16),
                Coordinate.of(1301, 16), Coordinate.of(1301, 11), Coordinate.of(1306, 11), Coordinate.of(1306, 6),
                Coordinate.of(1309, 6), Coordinate.of(1309, 17), Coordinate.of(1314, 17), Coordinate.of(1314, 19),
                Coordinate.of(1318, 19), Coordinate.of(1318, 20), Coordinate.of(1328, 20), Coordinate.of(1328, 16),
                Coordinate.of(1334, 16), Coordinate.of(1334, 18), Coordinate.of(1338, 18), Coordinate.of(1338, 20),
                Coordinate.of(1348, 20), Coordinate.of(1348, 16), Coordinate.of(1351, 16), Coordinate.of(1351, 8),
                Coordinate.of(1353, 8), Coordinate.of(1353, 11), Coordinate.of(1358, 11), Coordinate.of(1358, 12),
                Coordinate.of(1372, 12), Coordinate.of(1372, 17), Coordinate.of(1390, 17), Coordinate.of(1390, 13),
                Coordinate.of(1393, 13), Coordinate.of(1393, 19), Coordinate.of(1416, 19), Coordinate.of(1416, 11),
                Coordinate.of(1422, 11), Coordinate.of(1422, 15), Coordinate.of(1440, 15), Coordinate.of(1440, 9),
                Coordinate.of(1443, 9), Coordinate.of(1443, 6), Coordinate.of(1444, 6), Coordinate.of(1444, 15),
                Coordinate.of(1449, 15), Coordinate.of(1449, 19), Coordinate.of(1459, 19), Coordinate.of(1459, 18),
                Coordinate.of(1467, 18), Coordinate.of(1467, 10), Coordinate.of(1470, 10), Coordinate.of(1470, 16),
                Coordinate.of(1473, 16), Coordinate.of(1473, 18), Coordinate.of(1480, 18), Coordinate.of(1480, 19),
                Coordinate.of(1485, 19), Coordinate.of(1485, 18), Coordinate.of(1487, 18), Coordinate.of(1487, 20),
                Coordinate.of(1494, 20), Coordinate.of(1494, 17), Coordinate.of(1498, 17), Coordinate.of(1498, 20),
                Coordinate.of(1518, 20), Coordinate.of(1518, 19), Coordinate.of(1524, 19), Coordinate.of(1524, 20),
                Coordinate.of(1536, 20), Coordinate.of(1536, 14), Coordinate.of(1537, 14), Coordinate.of(1537, 17),
                Coordinate.of(1553, 17), Coordinate.of(1553, 16), Coordinate.of(1562, 16), Coordinate.of(1562, 15),
                Coordinate.of(1572, 15), Coordinate.of(1572, 20), Coordinate.of(1585, 20), Coordinate.of(1585, 18),
                Coordinate.of(1588, 18), Coordinate.of(1588, 17), Coordinate.of(1589, 17), Coordinate.of(1589, 19),
                Coordinate.of(1595, 19), Coordinate.of(1595, 20), Coordinate.of(1609, 20), Coordinate.of(1609, 15),
                Coordinate.of(1621, 15), Coordinate.of(1621, 12), Coordinate.of(1622, 12), Coordinate.of(1622, 11),
                Coordinate.of(1623, 11), Coordinate.of(1623, 15), Coordinate.of(1636, 15), Coordinate.of(1636, 20),
                Coordinate.of(1651, 20), Coordinate.of(1651, 19), Coordinate.of(1656, 19), Coordinate.of(1656, 20),
                Coordinate.of(1665, 20), Coordinate.of(1665, 19), Coordinate.of(1666, 19), Coordinate.of(1666, 18),
                Coordinate.of(1669, 18), Coordinate.of(1669, 17), Coordinate.of(1673, 17), Coordinate.of(1673, 15),
                Coordinate.of(1675, 15), Coordinate.of(1675, 17), Coordinate.of(1676, 17), Coordinate.of(1676, 18),
                Coordinate.of(1683, 18), Coordinate.of(1683, 17), Coordinate.of(1688, 17), Coordinate.of(1688, 15),
                Coordinate.of(1689, 15), Coordinate.of(1689, 12), Coordinate.of(1694, 12), Coordinate.of(1694, 11),
                Coordinate.of(1695, 11), Coordinate.of(1695, 15), Coordinate.of(1696, 15), Coordinate.of(1696, 20),
                Coordinate.of(1700, 20), Coordinate.of(1700, 11), Coordinate.of(1701, 11), Coordinate.of(1701, 16),
                Coordinate.of(1704, 16), Coordinate.of(1704, 20), Coordinate.of(1721, 20), Coordinate.of(1721, 17),
                Coordinate.of(1725, 17), Coordinate.of(1725, 15), Coordinate.of(1727, 15), Coordinate.of(1727, 17),
                Coordinate.of(1745, 17), Coordinate.of(1745, 15), Coordinate.of(1762, 15), Coordinate.of(1762, 14),
                Coordinate.of(1764, 14), Coordinate.of(1764, 16), Coordinate.of(1775, 16), Coordinate.of(1775, 12),
                Coordinate.of(1779, 12), Coordinate.of(1779, 16), Coordinate.of(1801, 16), Coordinate.of(1801, 20),
                Coordinate.of(1802, 20), Coordinate.of(1802, 16), Coordinate.of(1807, 16), Coordinate.of(1807, 0),
                Coordinate.of(1808, 0), Coordinate.of(1808, 9), Coordinate.of(1810, 9), Coordinate.of(1810, 14),
                Coordinate.of(1813, 14), Coordinate.of(1813, 12), Coordinate.of(1815, 12), Coordinate.of(1815, 14),
                Coordinate.of(1826, 14), Coordinate.of(1826, 16), Coordinate.of(1833, 16), Coordinate.of(1833, 19),
                Coordinate.of(1843, 19), Coordinate.of(1843, 18), Coordinate.of(1854, 18), Coordinate.of(1854, 20),
                Coordinate.of(1866, 20), Coordinate.of(1866, 16), Coordinate.of(1876, 16), Coordinate.of(1876, 15),
                Coordinate.of(1882, 15), Coordinate.of(1882, 20), Coordinate.of(1900, 20), Coordinate.of(1900, 16),
                Coordinate.of(1906, 16), Coordinate.of(1906, 13), Coordinate.of(1908, 13), Coordinate.of(1908, 12),
                Coordinate.of(1912, 12), Coordinate.of(1912, 9), Coordinate.of(1915, 9), Coordinate.of(1915, 20),
                Coordinate.of(1919, 20), Coordinate.of(1919, 16), Coordinate.of(1924, 16), Coordinate.of(1924, 20),
                Coordinate.of(1937, 20), Coordinate.of(1937, 19), Coordinate.of(1943, 19), Coordinate.of(1943, 20),
                Coordinate.of(1944, 20), Coordinate.of(1944, 19), Coordinate.of(1947, 19), Coordinate.of(1947, 14),
                Coordinate.of(1956, 14), Coordinate.of(1956, 10), Coordinate.of(1966, 10), Coordinate.of(1966, 9),
                Coordinate.of(1967, 9), Coordinate.of(1967, 17), Coordinate.of(1976, 17), Coordinate.of(1976, 18),
                Coordinate.of(1983, 18), Coordinate.of(1983, 15), Coordinate.of(1987, 15), Coordinate.of(1987, 18),
                Coordinate.of(1994, 18), Coordinate.of(1994, 20), Coordinate.of(2005, 20), Coordinate.of(2005, 15),
                Coordinate.of(2010, 15), Coordinate.of(2010, 0)

        ));
    }
}
