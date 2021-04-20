import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    private String message;
    //randomly add integers into the deque using StdRandom.uniform
    private void addInt(StudentArrayDeque<Integer> ad, ArrayDequeSolution<Integer> adSol, Integer i, double random) {
        //@source: StudentArrayDequeLauncher
        if (random < 0.5) {
            ad.addLast(i);
            adSol.addLast(i);
            message = "addLast: " + i;
        } else {
            ad.addFirst(i);
            adSol.addFirst(i);
            message = "addFirst: " + i;
        }
    }
    //randomly remove integers
    private void removeInt(StudentArrayDeque<Integer> ad, ArrayDequeSolution<Integer> adSol, double random) {
        Integer expected;
        Integer actual;
        if (random < 0.5) {
            expected = adSol.removeLast();
            actual = ad.removeLast();
            message = "removeLast()";
        } else {
            expected = adSol.removeFirst();
            actual = ad.removeFirst();
            message = "removeFirst()";
        }
        assertEquals(message, expected, actual);
    }

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> ad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> adSol = new ArrayDequeSolution<>();

        for (Integer i = 0; i < 10; i++) {
            if (ad.isEmpty()) {
                double random = StdRandom.uniform();
                addInt(ad, adSol, i, random);
            } else {
                double random = StdRandom.uniform();
                removeInt(ad, adSol, random);
            }
        }
    }
}



