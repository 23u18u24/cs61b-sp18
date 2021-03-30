import org.junit.Test;
import static org.junit.Assert.*;

public class HorribleSteve {

    @Test(timeout = 100)
    public static void testFlik(int i, int j) {
        assertTrue(Flik.isSameNumber(i, j));
    }

    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            testFlik(i, j);
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        testFlik(i, 500);
        System.out.println("i is " + i);
    }
}
