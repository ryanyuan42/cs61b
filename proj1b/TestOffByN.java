import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertFalse(offByN.equalChars('f', 'h'));
    }

    @Test
    public void testIsPalindrome() {
        OffByN cc = new OffByN(5);
        Palindrome palindrome = new Palindrome();
        assertTrue(palindrome.isPalindrome("abegf", cc));
        assertFalse(palindrome.isPalindrome("racecar", cc));
    }
}
