public class ArrayDeque<T> {

    private int size = 0;
    private int nextFirst = 4;
    private int nextLast = 5;
    private T[] arr;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
    }

    public void addFirst(T item) {
        if (size > 8 && size <= 16) {
            T[] newarr = (T[]) new Object[size + 1];
            System.arraycopy(arr, nextFirst + 1, newarr, nextFirst + 1, nextLast - 1);
            arr = newarr;
        } else if (size > 16) {
            T[] newarr = (T[]) new Object[size * 4];
            System.arraycopy(arr, nextFirst + 1, newarr, nextFirst + 1, nextLast - 1);
            arr = newarr;
        }
        if (nextFirst < 0) {
            nextFirst += size;
        }
        arr[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        int mass = 8;
        if (size > 8 && size <= 16) {
            mass = size + 1;
            T[] newarr = (T[]) new Object[mass];
            System.arraycopy(arr, nextFirst + 1, newarr, nextFirst + 1, nextLast - 1);
            arr = newarr;
        } else if (size > 16) {
            mass = size * 4;
            T[] newarr = (T[]) new Object[mass];
            System.arraycopy(arr, nextFirst + 1, newarr, nextFirst + 1, nextLast - 1);
            arr = newarr;
        }
        if (nextLast > mass) {
            nextLast -= size;
        }
        arr[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return nextLast - nextFirst == 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst + 1; i < size; i += 1) {
            System.out.print(arr[i] + " ");
        }
    }

    public T removeFirst() {
        T temp = arr[nextFirst + 1];
        arr[nextFirst + 1] = null;
        nextFirst += 1;
        size -= 1;
        return temp;
    }

    public T removeLast() {
        T temp = arr[nextLast - 1];
        arr[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;
        return temp;
    }

    public T get(int index) {
        return arr[nextFirst + index];
    }

}