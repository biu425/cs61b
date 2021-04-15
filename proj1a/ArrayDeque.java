public class ArrayDeque<T> {
    private static int initialCapacity = 8; //starting size of array is 8
    private static double minRatio = 0.25; //usage factor should be at least 25%

    private T[] t;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    //creates an empty array deque
    public ArrayDeque() {
        t = (T[]) new Object[initialCapacity];
        capacity = initialCapacity;
        size = 0;
        nextFirst = 0;
        nextLast = nextFirst + 1;
    }

    public void addFirst(T item) {
        expand();
        t[nextFirst] = item;
        nextFirst = indexMinus(nextFirst);
        size++;
    }

    public void addLast(T item) {
        expand();
        t[nextLast] = item;
        nextLast = indexPlus(nextLast);
        size++;
    }

    //get the current index according to the circular structure
    private int indexPlus(int index) {
        if (index == capacity - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int indexMinus(int index) {
        if (index == 0) {
            return capacity - 1;
        } else {
            return index - 1;
        }
    }

    //resizing the deque when it is full
    private void expand() {
        if (size == capacity) {
            int newCapacity = capacity * 2;
            resize(newCapacity);
        }
    }

    //resizing the deque when its usage lower than mRatio
    private void contract() {
        double ratio = (double) size / capacity;
        if (ratio < minRatio) {
            int newCapacity = capacity / 2;
            resize(newCapacity);
        }
    }

    //NEED TO CHECK HOW TO DECIDE THE NEXT INDEX
    private void resize(int newCapacity) {
        T[] newT = (T[]) new Object[newCapacity];
        int currentFirst = indexPlus(nextFirst);
        int currentLast = indexMinus(nextLast);

        if (nextFirst < nextLast ) {
            System.arraycopy(t, currentFirst, newT, 0, size);
        } else {
            int lengthFirst = capacity - currentFirst;
            System.arraycopy(t, currentFirst, newT, 0, lengthFirst);
            int lengthLast = nextLast;
            System.arraycopy(t, 0, newT, lengthFirst, lengthLast);
        }
        nextFirst = newCapacity - 1;
        capacity = newCapacity;
        nextLast = size;
        t = newT;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = indexPlus(nextFirst);
        for (int i = 0; i < size; i++) {
            if (nextFirst > nextLast) {
                index = (indexPlus(nextFirst) + index + capacity) % capacity;
            }
            System.out.print(t[index] + " ");
            index++;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int currentFirst = indexPlus(nextFirst);
        T removedItem = t[currentFirst];
        t[currentFirst] = null;
        nextFirst = currentFirst;
        size--;

        contract();
        return removedItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int currentLast = indexMinus(nextLast);
        T removedItem = t[currentLast];
        t[currentLast] = null;
        nextLast = currentLast;
        size--;
        contract();
        return removedItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int getIndex = (nextFirst + 1 + index) % capacity;

        return t[getIndex];
    }
}
