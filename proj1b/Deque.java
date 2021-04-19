public interface Deque<Item> {
    void addLast(Item item);
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
}
