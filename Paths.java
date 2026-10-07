/**
 * Class Paths to store an array of Path objects
 */
public class Paths {
    /**
     * paths stores an array of Path objects
     */
    private Path[] paths;
    /**
     * Records the size of paths array to add and remove correct element
     */
    private int size;

    /**
     * Default constructor creating empty paths array of size 10000, initializing size to 0
     */
    public Paths() {
        paths = new Path[10000];
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
     * Mutator to add Path object to paths array
     * @param p Path to be added to array
     */
    public void add(Path p) {
        paths[size] = p;
        size++;
    }

    /**
     * Mutator to remove last Path object from paths array
     */
    public void remove(){
        paths[size - 1] = null;
        size--;
    }

    /**
     * Accessor for the Path at index i; throws exception if i not valid
     * @param i index in paths array to be returned
     * @return Path at index i
     */
    public Path get(int i) {
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return paths[i];
    }
}