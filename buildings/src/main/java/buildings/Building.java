package buildings;

/**
 * This class represents a front view of a rectangular building.
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

    @Override
    public String toString() {
        return "buildings.Building{" +
                "left=" + left +
                ", right=" + right +
                ", height=" + height +
                '}';
    }
}
