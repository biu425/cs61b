package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    @Override
    Iterator<T> iterator();

    //return size of the buffer
    int capacity();

    //return number of items currently in the buffer
    int fillCount();

    //add item x th the end
    void enqueue(T x);

    //delete and return item from the front
    T dequeue();

    //return (not delete) item from the front
    T peek();

    //is the buffer empty(fillCount = 0)?
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    //is the buffer full(fillCount == capacity)?
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
