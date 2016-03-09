package knight;

import math.Coordinate;

/**
 * Created by jiri.peinlich on 20/01/2016.
 */
public class Board {

    int[][] board;

    public Board(int i) {
        board = new int[i][i];
    }

    public void set(Coordinate c, int val) {
        board[c.getX()][c.getY()] = val;
    }

    public Coordinate find(int number) {
        for (int i = 0; i < board.length; i++) {
            int[] ints = board[i];
            for (int j = 0; j < ints.length; j++) {
                int anInt = ints[j];
                if (anInt == number) {
                    return new Coordinate(i, j);
                }
            }
        }
        throw new RuntimeException("Could not find..");
    }

    public Integer get(Coordinate coordinate) {
        try {
            return board[coordinate.getX()][coordinate.getY()];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");

        for (int i = 0; i < board.length; i++) {
            int[] ints = board[i];
            for (int j = 0; j < ints.length; j++) {
                int anInt = ints[j];
                builder.append(anInt).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public int getSize() {
        return board.length;
    }
}
