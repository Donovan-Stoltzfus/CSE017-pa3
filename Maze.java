import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Maze {
    private char[][] maze;
    private int rows;
    private int cols;
    protected long recursions;

    public Maze(int rs, int cs) {
        rows = rs;
        cols = cs;
        maze = new char[rs][cs];
    }

    public void load(String filename) {
        try {
            File file = new File(filename);
            Scanner readFile = new Scanner(file);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    maze[r][c] = readFile.next().charAt(0);
                }
            }
            readFile.close();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public char getPoint(int row, int col) {
        if (row > rows || row < 0 || col > cols || col < 0) {
            throw new ArrayIndexOutOfBoundsException;
        }
        return maze[row][col];
    }

    public void setPoint(int row, int col, char c) {
        if (row > rows || row < 0 || col > cols || col < 0) {
            throw new ArrayIndexOutOfBoundsException;
        }
        maze[row][col] = c;
    }

    public boolean isValid(Coordinate c) {
        int row = c.row();
        int col = c.col();
        if (row > rows || row < 0 || col > cols || col < 0) {
            return false;
        } else {
            return true;
        }
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