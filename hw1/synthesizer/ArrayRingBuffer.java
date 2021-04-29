package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    private int first;            // index for the next dequeue or peek
    private int last;            //Index for the next enqueue.
    private T[] rb;             //Array for storing the buffer data.

    //Create a new ArrayRingBuffer with the given capacity.
    public ArrayRingBuffer(int capacity) {
        //  Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */

    //help method for first and last index
    private int indexPlus(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return index + 1;
    }

    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = indexPlus(last);
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T deItem = rb[first];
        rb[first] = null;
        first = indexPlus(first);
        fillCount--;

        return deItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        //Return the first item. None of your instance variables should change.
        return rb[first];
    }


    //When you get to part 5, implement the needed code to support iteration.
    private class queIterator implements Iterator<T> {
        int ptr;
        int remain;

        queIterator() {
            ptr = 0;
            remain = fillCount();
        }

        @Override
        public boolean hasNext() {
            return (remain != 0);
        }

        @Override
        public T next() {
            T returnItem = rb[ptr];
            ptr = indexPlus(ptr);
            remain--;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new queIterator();
    }
}
