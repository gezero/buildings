/**
 * Created by jiri.peinlich on 04/12/2015.
 */
public class Building {
    final int left;
    final int right;
    final int height;

    public Building(int left, int right, int height) {
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }
}
