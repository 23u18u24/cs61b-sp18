public class ArrayDeque<T> {

    private int size = 0;
    private int mass = 8;
    private int nextFirst = 0;
    private int nextLast = 1;
    private T[] arr;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
    }

    private void expend() {
        if (size >= mass) {
            int i = nextFirst + 1;
            if (size < 16) {
                mass += 1;
                nextFirst = size;
                nextLast = size;
            } else {
                mass *= 2;
            }
            T[] newarr = (T[]) new Object[mass];
            for (int j = 0; j < size; j += 1) {
                if (i == size) {
                    i = 0;
                    newarr[j] = arr[i];
                } else {
                    newarr[j] = arr[i];
                }
                i += 1;
            }
            arr = newarr;
        }
    }

    public void addFirst(T item) {
        expend();
        if (nextFirst < 0) {
            nextFirst += mass;
        }
        arr[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
    }

    public void addLast(T item) {
        expend();
        if (nextLast >= mass) {
            nextLast -= mass;
        }
        arr[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = 0;
        while (i < size) {
            if (arr[i] != null) {
                System.out.print(arr[i] + " ");
            }
            i += 1;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T temp = arr[nextFirst + 1];
        arr[nextFirst + 1] = null;
        nextFirst += 1;
        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T temp = arr[nextLast - 1];
        arr[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        return temp;
    }

    public T get(int index) {
        return arr[nextFirst + index + 1];
    }
}