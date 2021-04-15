/** Array based list.
 *  @author Josh Hug
 */

 /**       0 1  2 3 4 5 6 
  * items:[6 9 -1 2 0 0 0...] 
  * size:4
  */
 
 /**Invariants:
  *the position of the next item to be inserted (using addLast) is always size.
  *size is always the number of items in the AList.
  *the last item in the list is always in position [size-1].
  */
public class AList<Item> {
    private Item[] items;
    private int size;
    /** Creates an empty list. */
    
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }
    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity){
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a; 
    }
    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if(size == items.length){
        resize(size * RFACTOR); //resized by multiplying the number of boxes by RFACTOR;
        }
        items[size] = x;
        size += 1;
    }


    /** hint:for memory performance, 
     * we halve the size of the array when usage ratio falls to less than 0.25. 
     **/


    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        int x = getLast();
        items[size - 1] = null;
        size = size - 1;
        
        return x;
    }

    public static int[] insert(int[] arr, int item, int position){
        int[] resultArr = new int[arr.length + 1];
        position = Math.min(positon, arr.lenght);
        /** before the position, copy each[i] of arr */
        for(int i =0; i<position;i++){
            resultArr[i]=arr[i];
        }

        resultArr[position]=item;

        /** behind the positon, copy each[i+1] of arr to result[i]*/
        for(int i=positon;i <arr.length;i++){
            resultArr[i+1]=arr[i];
        }
        return resultArr;
     }

     public static void reverse(int[] arr){
        for(int i=0; i<arr.length/2; i++){
            int j = arr.length-i-1;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
     }

     public static int[] replicate(int[] arr){
         /** 新array, 长度, i*/
         int size=0;
         for(int item:arr){
             size += item;
         }
         int[] resultArr = new int[size];
         int i=0;
         for(int item:arr){
             for (int counter=0;counter<item;counter++){
                 resultArr[i]=item;
                 i++;
             }
         }
         return resultArr;

     }

} 