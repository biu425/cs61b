import org.junit.Test;

public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node previous;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            previous = p;
            next = n;
        }
    }

    /** create an empty linked list deque with circular sentinel*/
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;

        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(item, null, null);
        sentinel.previous = sentinel.next;
    }

    /** add and remove operations must not involve any looping or recursion */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.previous = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        size++;
        sentinel.previous = new Node(item, sentinel.previous, sentinel);
        sentinel.previous.previous.next = sentinel.previous;
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
        Node current = sentinel;
        while (current.next != sentinel) {
            System.out.print(current.next.item + " ");
            current = current.next;
        }
    }

    /** add and remove operations must not involve any looping or recursion */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;

        size--;
        return removedItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = sentinel.previous.item;
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;

        size--;
        return removedItem;
    }

    /** get must use iteration, and do not alter the deque */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node current = sentinel.next;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
