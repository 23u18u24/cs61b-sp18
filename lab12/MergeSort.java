import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>> makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> newQ = new Queue<>();
        for (Item item : items) {
            Queue<Item> q = new Queue<>();
            q.enqueue(item);
            newQ.enqueue(q);
        }
        return newQ;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        int count = 0;
        Queue<Item> newQ = new Queue<>();
        while (count < (q1.size() + q2.size())) {
            Item minN = getMin(q1, q2);
            newQ.enqueue(minN);
        }
        return newQ;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        Queue<Queue<Item>> doubleQ = makeSingleItemQueues(items);
//        int size = doubleQ.size();
//        Queue<Item> q1 = doubleQ.dequeue();
//        while (q1.size() != size) {
//            Queue<Item> q2 = doubleQ.dequeue();
//            q1 = mergeSortedQueues(q1, q2);
//            doubleQ.enqueue(q1);
//        }
//        return q1;
        Queue<Item> leftQ = new Queue<>();
        Queue<Item> rightQ = new Queue<>();
        int leftSize = doubleQ.size() / 2;
        for (int i = 0; i < leftSize; i++) {
            leftQ.enqueue(doubleQ.dequeue().dequeue());
        }
        int rightSize = doubleQ.size();
        for (int j = 0; j < rightSize; j++) {
            rightQ.enqueue(doubleQ.dequeue().dequeue());
        }
        return mergeSortedQueues(mergeSort(leftQ), mergeSort(rightQ));
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
        Queue<String> students = new Queue<String>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        printS(students);
        printS(mergeSort(students));
    }
}
