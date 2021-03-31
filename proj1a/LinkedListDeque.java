import java.util.LinkedList;

public class LinkedListDeque<T> {

    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentFront;
    private Node sentBack;
    private int size = 0;

    public LinkedListDeque() {
        sentFront = new Node(null, null, sentBack);
        sentBack = new Node(sentFront, null, null);
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
    }

    public void addFirst(T item) {
        Node n = new Node(sentFront, item, sentFront.next);
        sentFront.next.prev = n;
        sentFront.next = n;
        size += 1;
    }

    public void addLast(T item) {
        Node n = new Node(sentBack.prev, item, sentBack);
        sentBack.prev.next = n;
        sentBack.prev = n;
        size += 1;
    }

    public boolean isEmpty() {
        return sentFront.next == sentBack;
    }

    public int size() {
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
        T i = sentFront.next.item;
        sentFront.next.next.prev = sentFront;
        sentFront.next = sentFront.next.next;
        size -= 1;
        return i;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T i = sentBack.prev.item;
        sentBack.prev.prev.next = sentBack;
        sentBack.prev = sentBack.prev.prev;
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

    private T getHelper(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getHelper(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if (index > this.size() - 1) {
            return null;
        }
        return getHelper(index, sentFront.next);
    }
}