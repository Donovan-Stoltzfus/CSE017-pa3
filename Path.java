/**
 * Class path to represent array of coordinates to go through the maze
 */
public class Path implements Cloneable {
    /**
     * path stores an array of Coordinates
     */
    private Coordinate[] path;
    /**
     * Records the size of path array to add and remove correct element
     */
    private int size;

    /**
     * Default constructor; initializes empty path array of size 200, sets size to 0
     */
    public Path() {
        path = new Coordinate[200];
        size = 0;
    }

    /**
     * Accessor for the size
     * @return value of the size
     */
    public int size() {
        return size;
    }

    /**
     * Mutator to add Coordinate object to path array
     * @param c Coordinate to be added to array
     */
    public void add(Coordinate c) {
        path[size] = c;
        size++;
    }

    /**
     * Mutator to remove last Coordinate from path array
     */
    public void remove(Coordinate c) {
        path[size - 1] = null;
        size--;
    }

    /**
     * Accessor for the Coordinate at index i; throws exception if i not valid
     * @param i index in path array to be returned
     * @return Coordinate at index i
     */
    public Coordinate get(int i) throws ArrayIndexOutOfBoundsException{
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return path[i];
    }

    /**
     * Cloning method to clone Path object
     * @return cloned Path
     */
    public Object clone() {
        Path clonedPath = new Path();
        for (int i = 0; i < size; i++) {
            clonedPath.add(path[i]);
        }
        return clonedPath;
    }
}