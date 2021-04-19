import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.*/
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator ccOffByOne = new OffByOne();
    static CharacterComparator ccOffBy1 = new OffByN(1);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome0or1() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testIsPalindrome() {
        String sTure = "noon";
        String sFalse = "cat";
        assertTrue(palindrome.isPalindrome(sTure));
        assertFalse(palindrome.isPalindrome(sFalse));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String sTrue = "";
        String sFalse = "a";
        assertTrue(palindrome.isPalindrome(sTrue, ccOffByOne));
        assertTrue(palindrome.isPalindrome(sFalse, ccOffByOne));
    }

    @Test
    public void testIsPalindromeOffBy1() {
        String sTrue = "flake";
        String sFalse = "flaka";
        assertTrue(palindrome.isPalindrome(sTrue, ccOffBy1));
        assertFalse(palindrome.isPalindrome(sFalse, ccOffBy1));
    }
}
