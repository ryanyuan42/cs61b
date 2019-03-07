import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();


    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }

    @Test
    public void testIsPalindrome() {
        OffByOne cc = new OffByOne();
        Palindrome palindrome = new Palindrome();

        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("flke", cc));
        assertFalse(palindrome.isPalindrome("racecar", cc));
    }
}
