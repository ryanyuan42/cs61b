import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }

    @Test
    public void testIsPalindrome(){
        OffByOne cc = new OffByOne();
        assertTrue(cc.isPalindrome("flake", cc));
        assertTrue(cc.isPalindrome("flke", cc));
        assertFalse(cc.isPalindrome("racecar", cc));
    }

    // Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
