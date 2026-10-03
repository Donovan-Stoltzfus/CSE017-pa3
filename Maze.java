public class Maze {
    private char[][] maze;
    private int rows;
    private int cols;
    protected long recursions;

    public Maze(int rs, int cs) {
        rows = rs;
        cols = cs;
    }

    public void load(String filename) {

    }

    public char getPoint(int row, int col) {
        
    }

    public void setPoint(int row, int col, char c) {

    }

    public boolean isValid(Coordinate c) {

    }

    public Path findPath(Coordinate start, Coordinate end) {

    }

    public boolean findPath(Coordinate current, Coordinate end, Path currentPath, boolean[][] visited) {

    }

    public Paths findAllPaths(Coordinate start, Coordinate end) {

    }

    public void findAllPaths(Coordinate current, Coordinate end, Path currentPath, Paths paths, boolean[][] visited) {

    }

    public Object clone() {

    }

    public String toString() {

    }

    public void visualPath() {
        
    }
}