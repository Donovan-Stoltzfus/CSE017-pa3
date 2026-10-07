/**
 * Class Coordinate to represent row, col on Maze
 */
public class Coordinate {
    /**
     * row, col represent location on Maze
     */
    private int row;
    private int col;

    /**
     * Constructor with 2 parameters
     * @param r represents row of maze
     * @param c represents column of maze
     */
    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    /**
     * Accessor for the row
     * @return value of the row
     */
    public int row() {
        return row;
    }

    /**
     * Accessor for the col
     * @return value of the col
     */
    public int col() {
        return col;
    }

    /**
     * Checks this Coordinate and another Coordinate for equality
     * @param c Coordinate to be compared
     * @return true, false representing equality
     */
    public boolean equals(Coordinate c) {
        return row == c.row() && col == c.col();
    }
}