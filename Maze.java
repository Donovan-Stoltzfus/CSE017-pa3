import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Maze {
    private char[][] maze;
    private int rows;
    private int cols;
    protected static long recursions;

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
            throw new ArrayIndexOutOfBoundsException();
        }
        return maze[row][col];
    }

    public void setPoint(int row, int col, char c) {
        if (row > rows || row < 0 || col > cols || col < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        maze[row][col] = c;
    }

    public boolean isValid(Coordinate c) {
        int row = c.row();
        int col = c.col();
        if (row >= rows || row < 0 || col >= cols || col < 0) {
            return false;
        } else {
            return true;
        }
    }

    public Path findPath(Coordinate start, Coordinate end) {
        recursions = 0;
        Path path = new Path();
        boolean[][] visited = new boolean[rows][cols];
        findPath(start, end, path, visited);
        return path;
    }

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

    public Paths findAllPaths(Coordinate start, Coordinate end) {
        recursions = 0;
        Path path = new Path();
        Paths paths = new Paths();
        boolean[][] visited = new boolean[rows][cols];
        findAllPaths(start, end, path, paths, visited);
        return paths;
    }

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

    public String toString() {
        String formatted = "Maze with solution path\n";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                formatted += this.getPoint(r, c) + "  ";
            }
            formatted += "\n";
        }
        return formatted;
    }

    public void visualizePath(Path p) {
        Maze copy = (Maze) this.clone();
        for (int i = 1; i < p.size() - 1; i++) {
            Coordinate c = p.get(i);
            copy.setPoint(c.row(), c.col(), '*');
        }
        System.out.println(copy.toString());
    }
}