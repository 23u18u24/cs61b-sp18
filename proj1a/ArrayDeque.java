public class ArrayDeque<T> {

    public int size;
    public T[] arr;

    public ArrayDeque() {
        arr = (T[])new Object[8];
        arr = null;
        size = 0;
    }

    public void addFirst(T item) {
        if (arr[0] != null && size <= 8) {
            for (int i = 0; i < size; i += 1) {
                arr[i + 1] = arr[i];
            }
        } else if (size <= 16){
            T[] new_arr = (T[])new Object[size + 1];
            System.arraycopy(arr, 0, new_arr, 1, size);
            arr = new_arr;
        } else {
            T[] new_arr = (T[])new Object[size * 4];
            System.arraycopy(arr, 0, new_arr, 1, arr.length);
            arr = new_arr;
        }
        arr[0] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size > 8) {
            T[] new_arr = (T[]) new Object[size + 1];
            System.arraycopy(arr, 0, new_arr, 0, size - 1);
            arr = new_arr;
        }
        arr[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return arr[0] == null;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            System.out.print(arr[i] + " ");
        }
    }

    public T removeFirst() {
        T temp = arr[0];
        for (int i = 0; i < size; i += 1) {
            arr[i] = arr[i + 1];
        }
        size -= 1;
        return temp;
    }

    public T removeLast() {
        T temp = arr[size - 1];
        arr[size - 1] = null;
        size -= 1;
        return temp;
    }

    public T get(int index) {
        return arr[index];
    }
}
