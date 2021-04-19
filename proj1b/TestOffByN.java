import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN {
    static CharacterComparator offBy3 = new OffByN(3);

    @Test
    public void testOffBy3() {
        assertTrue(offBy3.equalChars('A', 'D'));
        assertFalse(offBy3.equalChars('A', 'C'));
        assertTrue(offBy3.equalChars('D', 'A'));
        assertFalse(offBy3.equalChars('C', 'A'));
    }
}
