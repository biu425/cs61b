public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        T item;
        Node previous;
        Node next;

        Node(T i, Node p, Node n) {
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

    /** add and remove operations must not involve any looping or recursion */
    public void addFirst(T item) {
        sentinel.next = new Node(item,sentinel.previous,sentinel);
        sentinel.next.next.previous = sentinel.previous;
        size ++;
   }

   public void addLast(T item) {
        size ++;
        sentinel.previous = new Node(item,sentinel.previous,sentinel);
        sentinel.previous.previous.next = sentinel.previous;
   }

   public boolean isEmpty() {
        if(sentinel == sentinel.next && sentinel == sentinel.previous && size == 0) {
            return true;
        }
        return false;
   }

   public int size() {
        return size;
   }

   public void printDeque() {
        Node current = sentinel;
        while(current.next != sentinel) {
            System.out.print(current.next.item + " ");
            current = current.next;
        }
   }

   /** add and remove operations must not involve any looping or recursion */
    public T removeFirst() {
        if(sentinel.next == sentinel) {
            return null;
        }
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;

        size --;

        return removedItem;
   }

   public T removeLast() {
        if(sentinel.previous == sentinel) {
            return null;
        }
        T removedItem = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;

        size --;

        return removedItem;
   }

   /** get must use iteration, and do not alter the deque */
   public T get(int index) {
        if(index >= size) {
            return null;
        }
        Node current = sentinel.next;
        while(index > 0) {
            current = current.next;
            index --;
        }
        return current.item;
   }

   public T getRecursive(int index) {
       if(index >= size) {
           return null;
       }
       return getRecursiveHelper(sentinel.next, index);
   }

   public T getRecursiveHelper(Node node, int index) {
       if(index == 0) {
           return node.item;
       }
       return getRecursiveHelper(node.next, index -1);
   }
}
