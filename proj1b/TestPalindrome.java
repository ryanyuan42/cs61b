import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset. */

    static Palindrome palindrome = new Palindrome();

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
    public void testIsPalindrome() {


        CharacterComparator cc = new OffByN(1);
        assertTrue(palindrome.isPalindrome("fl !ke", cc));
        assertTrue(palindrome.isPalindrome("A", cc));
        assertTrue(palindrome.isPalindrome(" ", cc));
        assertTrue(palindrome.isPalindrome("!", cc));
        assertTrue(palindrome.isPalindrome("AB", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome("ab", cc));
        assertTrue(palindrome.isPalindrome("ba", cc));
        assertTrue(palindrome.isPalindrome("&%", cc));
        assertTrue(palindrome.isPalindrome("%&", cc));
        assertFalse(palindrome.isPalindrome("ac", cc));
        assertFalse(palindrome.isPalindrome("aa", cc));
        assertFalse(palindrome.isPalindrome("ad", cc));
        assertFalse(palindrome.isPalindrome("%!", cc));
        assertFalse(palindrome.isPalindrome("aaaaab", cc));
        assertFalse(palindrome.isPalindrome("rancor", cc));

        assertFalse(palindrome.isPalindrome("R@!!@r"));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertTrue(palindrome.isPalindrome("noon"));

    }
}
