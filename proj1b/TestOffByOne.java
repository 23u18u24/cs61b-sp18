import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    /*Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
    @Test
    public void equalChars() {
        assertTrue(offByOne.equalChars('a', 'a'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('1', '1'));
        assertTrue(offByOne.equalChars('!', '!'));

        assertFalse(offByOne.equalChars('a', 'A'));
        assertFalse(offByOne.equalChars('C', 'c'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('a', 'e'));
    }
}
