public class Path implements Cloneable {
    private Coordinate[] path;
    private int size;

    public Path() {
        path = new Coordinate[200];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(Coordinate c) {
        path[size] = c;
        size++;
    }

    public void remove(Coordinate c) {
        path[size - 1] = null;
        size--;
    }

    public Coordinate get(int i) throws ArrayIndexOutOfBoundsException{
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return path[i];
    }

    public Object clone() {
        Path clonedPath = new Path();
        for (int i = 0; i < size; i++) {
            clonedPath.add(path[i]);
        }
        return clonedPath;
    }
}