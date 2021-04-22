import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> ad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> adSol = new ArrayDequeSolution<>();
        String assertMessage = "";

        //add
        for (int i = 0; i < 10; i++) {
            double random0 = StdRandom.uniform();
            if (random0 < 0.5) {
                Integer random = StdRandom.uniform(10);
                ad.addLast(random);
                adSol.addLast(random);
                Integer expected = adSol.get(i);
                Integer actual = ad.get(i);
                //System.out.printf("addLast(%s)\n", random);
                assertMessage += "addLast(" + random + ")\n";
                assertEquals(assertMessage, expected, actual);
            } else {
                Integer random = StdRandom.uniform(10);
                ad.addFirst(random);
                adSol.addFirst(random);
                Integer expected = adSol.get(i);
                Integer actual = ad.get(i);
                //System.out.printf("addFirst(%s)\n", random);
                assertMessage += "addFirst(" + random + ")\n";
                assertEquals(assertMessage, expected, actual);
            }
        }

        //remove
        if (!ad.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                double random = StdRandom.uniform();
                if (random < 0.5) {
                    Integer actual = ad.removeLast();
                    Integer expected = adSol.removeLast();
                    //System.out.println("removeLast()");
                    assertMessage += "removeLast()\n";
                    assertEquals(assertMessage, expected, actual);
                } else {
                    Integer actual = ad.removeFirst();
                    Integer expected = adSol.removeFirst();
                    //System.out.println("removeFirst()");
                    assertMessage += "removeFirst()\n";
                    assertEquals(assertMessage, expected, actual);
                }
            }
        }
    }
}
