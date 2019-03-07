import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.

    @Test
    public void testEqualChars(){
        assertTrue(offByN.equalChars('a', 'f'));
        assertFalse(offByN.equalChars('f', 'h'));
    }

    @Test
    public void testIsPalindrome(){
        OffByN cc = new OffByN(5);
        assertTrue(cc.isPalindrome("abegf", cc));
        assertFalse(cc.isPalindrome("racecar", cc));
    }

    // Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
