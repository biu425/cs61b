public class IntList {
    public int first;
    public IntList rest;

    public void skippyfy() {
        IntList p = this;
        int n = 1;
        while (p != null) {
            IntList next = p.rest;
            for (int i = 0; i < n; i++) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            p = p.rest;
            n += 1;
        }
    }

    public int first;
    public IntList rest;
    public IntList(int f, IntList r) {
        this.first = f;
        this.rest = r;
    }

    /** given the sorted linked list of items - remove duplicates.
     * for example given 1-2-2-2-3,
     * mutate it to become 1-2-3(destructively)
     */
    public static void removeDuplicates(IntList p) {
        if (p == null) {
            return;
        }
        IntList current = p.rest;
        IntList previous = p;
        while (current != null) {
            if (current.first == previous.first) {
                previous.rest = current.rest;
            } else {
                previous = current;
            }
            current = current.rest;
        }
    }
}