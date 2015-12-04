import java.util.Collection;
import java.util.List;

/**
 */
public interface Silhouette {
    List<Coordinate> calculateSilhouette(Collection<Building> buildings);
}
