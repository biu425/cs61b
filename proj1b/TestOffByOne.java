import static org.junit.Assert.*;

import org.junit.Test;

public class TestOffByOne {
    /*
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.*/
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOneUpper() {
        assertTrue(offByOne.equalChars('A', 'B'));
        assertFalse(offByOne.equalChars('A', 'C'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('C', 'A'));
        assertFalse(offByOne.equalChars('A', 'A'));
    }

    @Test
    public void testOffByOneLower() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertFalse(offByOne.equalChars('c', 'a'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }

    @Test
    public void testOffByOneOther() {
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('&', '*'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('*', '&'));
    }

    @Test
    public void testOffByOneMix() {
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('B', 'a'));
    }
}
