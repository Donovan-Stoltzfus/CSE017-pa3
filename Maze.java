import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Represents a Maze with start, end, walls
 */
public class Maze {
    /**
     * maze stores identity of every coordinate in map
     */
    private char[][] maze;
    /**
     * rows, cols to represent size of maze
     */
    private int rows;
    private int cols;
    /**
     * static recursions to represent number of times recursive methods are called
     */
    protected static long recursions;

    /**
     * Constructor with two parameters
     * @param rs represents rows in maze
     * @param cs represents cols in maze
     */
    public Maze(int rs, int cs) {
        rows = rs;
        cols = cs;
        maze = new char[rs][cs];
    }

    /**
     * Reads maze from file
     * @param filename to be read
     */
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

    /**
     * Accessor for identity of point in maze
     * @param row of coordinate to be accessed
     * @param col of coordinate to be accessed
     * @return char stored in maze array at row, col
     */
    public char getPoint(int row, int col) {
        if (row > rows || row < 0 || col > cols || col < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return maze[row][col];
    }

    /**
     * Mutator for character in maze
     * @param row of coordinate to be changed
     * @param col of coordinate to be changed
     * @param c character to be added
     */
    public void setPoint(int row, int col, char c) {
        if (row > rows || row < 0 || col > cols || col < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        maze[row][col] = c;
    }

    /**
     * Checks if coordinate is in maze
     * @param c Coordinate to be checked
     * @return boolean representing if Coordinate is valid
     */
    public boolean isValid(Coordinate c) {
        int row = c.row();
        int col = c.col();
        if (row >= rows || row < 0 || col >= cols || col < 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method to find a possible path from start to end of maze
     * @param start represents beginning location on maze
     * @param end represents ending location on maze
     * @return Path object representing path from start to finish
     */
    public Path findPath(Coordinate start, Coordinate end) {
        recursions = 0;
        Path path = new Path();
        boolean[][] visited = new boolean[rows][cols];
        findPath(start, end, path, visited);
        return path;
    }

    /**
     * Recursive method to search for path from start to end of maze
     * @param current represents current location in maze
     * @param end represents ending location on maze
     * @param currentPath represents Path object with visited Coordinates
     * @param visited stores visited coordinates in maze
     * @return boolean representing if Coordinate is valid or not
     */
    public boolean findPath(Coordinate current, Coordinate end, Path currentPath, boolean[][] visited) {
        recursions++;
        if (!this.isValid(current)) {
            return false;
        }
        if (maze[current.row()][current.col()] == '#') {
            return false;
        }
        if (visited[current.row()][current.col()]) {
            return false;
        }
        if (current.equals(end)) {
            currentPath.add(current);
            return true;
        }
        visited[current.row()][current.col()] = true;
        currentPath.add(current);
        Coordinate nextCurrent = new Coordinate(current.row(), current.col() + 1);
        if (findPath(nextCurrent, end, currentPath, visited)) {
            return true;
        }
        nextCurrent = new Coordinate(current.row(), current.col() - 1);
        if (findPath(nextCurrent, end, currentPath, visited)) {
            return true;
        }
        nextCurrent = new Coordinate(current.row() - 1, current.col());
        if (findPath(nextCurrent, end, currentPath, visited)) {
            return true;
        }
        nextCurrent = new Coordinate(current.row() + 1, current.col());
        if (findPath(nextCurrent, end, currentPath, visited)) {
            return true;
        }
        currentPath.remove(current);
        visited[current.row()][current.col()] = false;
        return false;
    }

    /**
     * Method to find all possible paths from start to end of maze
     * @param start represents beginning location on maze
     * @param end represents ending location on maze
     * @return Paths object storing all possible Path objects
     */
    public Paths findAllPaths(Coordinate start, Coordinate end) {
        recursions = 0;
        Path path = new Path();
        Paths paths = new Paths();
        boolean[][] visited = new boolean[rows][cols];
        findAllPaths(start, end, path, paths, visited);
        return paths;
    }

    /**
     * Recursive method to search for all possible paths from start to end of maze
     * @param current represents current location in maze
     * @param end represents ending location on maze
     * @param currentPath represents current Path object, storing visited Coordinates
     * @param paths stores all Path objects
     * @param visited stores visited coordinates in maze
     * @return boolean representing if Coordinate is valid or not
     */
    public void findAllPaths(Coordinate current, Coordinate end, Path currentPath, Paths paths, boolean[][] visited) {
        //base cases - current position is not valid, #, visited, or ending location
        recursions++;
        if (!this.isValid(current)) {
            return;
        }
        if (maze[current.row()][current.col()] == '#') {
            return;
        }
        if (visited[current.row()][current.col()]) {
            return;
        }
        if (current.equals(end)) {
            currentPath.add(current);
            visited[current.row()][current.col()] = true;
            paths.add((Path)currentPath.clone());
            currentPath.remove(current);
            visited[current.row()][current.col()] = false;
            return;
        }
        visited[current.row()][current.col()] = true;
        currentPath.add(current);
        //recursive calls to left, right, up, down
        Coordinate nextCurrent = new Coordinate(current.row(), current.col() + 1);
        findAllPaths(nextCurrent, end, currentPath, paths, visited);
        nextCurrent = new Coordinate(current.row(), current.col() - 1);
        findAllPaths(nextCurrent, end, currentPath, paths, visited);
        nextCurrent = new Coordinate(current.row() - 1, current.col());
        findAllPaths(nextCurrent, end, currentPath, paths, visited);
        nextCurrent = new Coordinate(current.row() + 1, current.col());
        findAllPaths(nextCurrent, end, currentPath, paths, visited);
        //remove current coordinate and reset visited status so other paths can use its coordinate
        currentPath.remove(current);
        visited[current.row()][current.col()] = false;
        return;
    }

    /**
     * Cloning method to clone Maze object
     * @return cloned Maze
     */
    public Object clone() {
        Maze copy = new Maze(rows, cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char point = this.getPoint(r, c);
                copy.setPoint(r, c, point);
            }
        }
        return copy;
    }

    /**
     * Accessor to print all coordinates in Maze
     * @return formatted string with Maze info
     */
    public String toString() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                formatted += this.getPoint(r, c) + "  ";
            }
            formatted += "\n";
        }
        return formatted;
    }

    /**
     * Prints path through the maze
     * @param p path taken through maze
     */
    public void visualizePath(Path p) {
        Maze copy = (Maze) this.clone();
        for (int i = 1; i < p.size() - 1; i++) {
            Coordinate c = p.get(i);
            copy.setPoint(c.row(), c.col(), '*');
        }
        String formatted = "Maze with solution path\n";
        System.out.println(copy.toString());
    }
}