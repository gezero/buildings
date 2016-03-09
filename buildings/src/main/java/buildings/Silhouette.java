package buildings;

import math.Coordinate;

import java.util.Collection;
import java.util.List;

/**
 * Implementations of this interface should return a silhouette of a city. When you have a look at a city from a distance
 * you and you cannot distinguish buildings against each other, but you can still see a silhouette of a city.
 *
 * As an input you will get a collection of buildings. Each of the buildings have left and right coordinate and a height.
 * Your task is to return list of coordinates that represents the silhouette of the city when drawn from left.
 *
 * Refer to the tests for examples of what is required.
 *
 */
public interface Silhouette {
    List<Coordinate> calculateSilhouette(Collection<Building> buildings);
}
