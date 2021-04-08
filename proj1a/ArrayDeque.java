public class ArrayDeque<T> {

    private int size = 0;
    private int nextFirst = 0;
    private int nextLast = 1;
    private T[] arr;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
    }

    private int plusOne(int index) {
        return Math.floorMod(index + 1, arr.length);
    }

    private int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }

    private int minusOne(int index) {
        return Math.floorMod(index - 1, arr.length);
    }

    private void expand() {
        resizeHelper(arr.length * 2);
    }

    private void reduce() {
        resizeHelper(arr.length / 2);
    }

    private void resize() {
        if (size == arr.length) {
            expand();
        } else if (size < arr.length / 4 && arr.length > 8) {
            reduce();
        }
    }

    private void resizeHelper(int capacity) {
        T[] temp = arr;
        arr = (T[]) new Object[capacity];
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        nextFirst = 0;
        nextLast = 1;
        for (int i = begin; i != end; i = plusOne(i, temp.length)) {
            arr[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        arr[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    public void addFirst(T item) {
        resize();
        arr[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        resize();
        arr[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = arr[plusOne(nextFirst)];
        arr[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = arr[minusOne(nextLast)];
        arr[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        return temp;
    }

    public T get(int index) {
        return arr[Math.floorMod(plusOne(nextFirst) + index, arr.length)];
    }
}