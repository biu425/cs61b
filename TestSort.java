import static org.junit.Assert.*;

import org.junit.Test;


/** test the Sort class */
public class TestSort {
    /** test the Sort.sort method. */
    @Test
    public void testSort(){
        String[] input = {"i","have","an","egg"};
        String[] expected = {"an","egg","have","i"};

        Sort.sort(input);

        assertArraryEquals(expected, input);
    }

    /** test the Sort.findsmallest method. */
    @Test
    public void testFindSmallest(){
        String[] input = {"i","have","an","egg"};
        int expected = 2;

        int actual = Sort.findSmallest(input,0);
        assertEquals(expected,actual);

        String[] input2 = {"there","are","many","pigs"};
        int expected2 = 2;
        int actual2 = Sort.findSmallest(input2,2);
        assertEquals(expected2,actual2);
    }

    /** test the Sort.swap method. */
    @Test
    public void testSwap(){
        String[] input = {"i","have","an","egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an","egg","have","i"};

        Sort.swap(input, a, b);
        assertArraryEquals(expected, input);
    }
}
