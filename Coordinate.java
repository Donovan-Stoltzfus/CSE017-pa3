public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    public boolean equals(Coordinate c) {
        return row == c.row() && col == c.col();
    }
}