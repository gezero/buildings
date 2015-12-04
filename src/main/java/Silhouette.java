import java.util.Collection;

/**
 */
public interface Silhouette {
    Iterable<Coordinate> calculateSilhouette(Collection<Building> buildings);
}
