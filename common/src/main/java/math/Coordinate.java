package math;

/**
 * Created by jiri.peinlich on 20/01/2016.
 */
public class Coordinate {

    final int x;
    final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "C{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }
}
