public class SLList{
    public static class IntNode {
        public int item;
        public IntNode next;
    
        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    /**creates an empty SLList. */
    public SLList(){
        sentinel = new IntNode(25, null);
        size = 0;
    }

    public SLList(int x){
        sentinel = new IntNode(25, null);
        sentinel.next = new IntNode(x,null);
        size = 1;
    }

    /**Adds x to the front of the list. */
    public void addFirst(int x){
        sentinel.next = new IntNode(x,sentinel.next);
        size += 1;
    }

    /**Returns to the fist item of the list. */
    public int getFirst(){
        return sentinel.next.item;
    }

    /**Adds an item to the end of the list. */
    public void addLast(int x){
        size += 1;
       
        IntNode p = sentinel;
        /**move p until it reaches the end of the list.  */
        while(p.next != null){
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    // /**returns the size of the list that starts at IntNode p. */
    // private static int size(IntNode p){
    //     if (p.next == null){
    //         return 1;
    //     }

    //     return 1 + size(p.next);
    // }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addLast(20);
        System.out.println(L.size());
        
    }
}
