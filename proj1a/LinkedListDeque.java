import java.util.LinkedList;

public class LinkedListDeque<T> {

    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public Node sentFront;
    public Node sentBack;
    public int size = 0;

    public LinkedListDeque() {
        sentFront = new Node(null, null, sentBack);
        sentBack = new Node(sentFront, null, null);
        sentFront = new Node(null, null, sentBack);
    }

    public void addFirst(T item) {
        Node n = new Node(sentFront, item, sentFront.next);
        sentFront.next = n;
        size += 1;
    }

    public void addLast(T item) {
        Node n = new Node(sentBack.prev, item, sentBack);
        sentBack.prev = n;
        size += 1;
    }

    public boolean isEmpty() {
        return sentFront.next == sentBack;
    }

    public int size() {
        /*int s = 0;
        Node p = sentFront.next;
        for (;p != sentBack; s += 1) {
            p = p.next;
        }
        return s;*/
        return size;
    }

    public void printDeque() {
        Node p = sentFront.next;
        while (p != sentBack) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Node p = sentFront.next;
        T i = p.item;
        p.next.prev = sentFront;
        sentFront.next = p.next;
        size -= 1;
        return i;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node p = sentBack.prev;
        T i = p.item;
        p.prev.next = sentBack;
        sentBack.prev = p.prev;
        size -= 1;
        return i;
    }

    public T get(int index) {
        if (index > this.size() - 1) {
            return null;
        }
        Node p = sentFront.next;
        for (int i = 0; i < index; i += 1) {
            p = p.next;
        }
        return p.item;
    }

    public T getHelper(int index, Node p) {
        if (index == 1) {
            return p.item;
        }
        return getHelper(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if (index > this.size() - 1) {
            return null;
        }
        Node p = sentFront.next;
        return getHelper(index, p);
    }
}
