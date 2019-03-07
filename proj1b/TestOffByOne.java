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
        assertTrue(cc.isPalindrome("flake", cc));
        assertTrue(cc.isPalindrome("flke", cc));
        assertFalse(cc.isPalindrome("racecar", cc));
    }
}
