public class Sort{
    /** sort strings in detructive way. */
    public static void sort(String[] x) {
        sort(x,0);
    }

    /** sorts x starting at position start. */
    public static void sort(String[] x, int start){
        if (start == x.length){ /** basic case */
            return;
        }
        /** three steps */
        int smallesetIndex = findSmallest(x, start);
        swap(x, start, smallesetIndex);
        sort(x, start+1);
    }

    /** return the index of the smallest String in x, starting at position start. */
    public static int findSmallest(String[] x, int start){
        int smallestIndex = start;
        for(int i = start; i < x.length; i++ ){
             int cmp = x[i].compareTo(x[smallestIndex]); /** if x[i] < x[smallesetIndex], cmp = -1 */
            if (cmp < 0){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    /** swap items with b. */
    public static void swap(String[] x, int a, int b){
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}