
/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = 0;
        for (String s : asciis) {
            max = max > s.length() ? max : s.length();
        }
        for (int i = max; i > 0; i--) {
            sortHelperLSD(asciis, i);
        }
        return null;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int min = (int) asciis[0].charAt(0);
        int max = 0;
        int[] arr = new int[256];
        for (int i = 0; i < asciis.length; i++) {
            String s = asciis[i];
            if (index > s.length()) {
                arr[0]++;
            } else {
                int c = (int) s.charAt(index - 1);
                min = min < c ? min : c;
                max = max > c ? max : c;
                arr[c]++;
            }
        }
        for (int i = min; i < max + 1; i++) {
            arr[i] += arr[i - 1];
        }
        String[] new_asciis = new String[asciis.length];
        for (int i = asciis.length - 1; i >= 0; i--) {
            String s = asciis[i];
            if (index > s.length()) {
                new_asciis[--arr[0] - 1] = s;
            } else {
                int c = (int) s.charAt(index - 1);
                new_asciis[--arr[c]] = s;
            }
        }
        System.arraycopy(new_asciis, 0, asciis, 0, new_asciis.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
