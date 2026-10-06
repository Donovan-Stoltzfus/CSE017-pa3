public class Paths {
    private Path[] paths;
    private int size;

    public Paths() {
        paths = new Path[10000];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(Path p) {
        paths[size] = p;
        size++;
    }

    public void remove(){
        paths[size - 1] = null;
        size--;
    }

    public Path get(int i) {
        if (i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return paths[i];
    }
}