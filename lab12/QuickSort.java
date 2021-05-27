import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     * <p>
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item : q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted A Queue of unsorted items
     * @param pivot    The item to pivot on
     * @param less     An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are less than the given pivot.
     * @param equal    An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are equal to the given pivot.
     * @param greater  An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item item : unsorted){
            if (item.compareTo(pivot) == 0) {
                equal.enqueue(item);
            } else if (item.compareTo(pivot) > 0) {
                greater.enqueue(item);
            } else {
                less.enqueue(item);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> quickSort(Queue<Item> items) {
        // Your code here!
        Item pivot = getRandomItem(items);
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        Queue<Item> newQ1 = new Queue<>();
        Queue<Item> newQ2 = new Queue<>();
        partition(items, pivot, less, equal, greater);
        if (less.size() >= 1) {
            newQ1 = quickSort(less);
        }
        if (greater.size() >= 1) {
            newQ2 = quickSort(greater);
        }
        return catenate(catenate(newQ1, equal), newQ2);
    }

    private static <Item extends Comparable> void printS(Queue<Item> q) {
        int count = 0;
        while (count < q.size()) {
            Item item = q.dequeue();
            q.enqueue(item);
            System.out.print(item);
            System.out.print(" ");
            count++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> students = new Queue<Integer>();
        students.enqueue(0);
        students.enqueue(1);
        students.enqueue(1);
        students.enqueue(2);
        students.enqueue(3);
        students.enqueue(4);
        students.enqueue(5);
        students.enqueue(5);
        students.enqueue(6);
        students.enqueue(5);
        printS(students);
        Queue<Integer> sort_students = quickSort(students);
        printS(sort_students);
    }
}
