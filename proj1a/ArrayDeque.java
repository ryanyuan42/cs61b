/**
 * @author Ryan Yuan ryanyuan42@gmail.com
 *         Created on 2019/3/7.
 */
public class ArrayDeque<T> {
    /** Items, the array contains all the elements of the deque. */
    private T[] items;
    /** The size of deque. */
    private int size;
    /** nextFirst points to the next first of the deque. */
    private int nextFirst;
    /** nextLast points to the next last of the deque. */
    private int nextLast;
    /** resize factor. */
    private int factor = 2;

    /**
     * Creates an empty array deque.
     * */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Resize the array list to the capacity.
     * @param target integer, the length of the new array.
     * */
    private void resize(int target) {
        T[] oldItems = items;
        int oldFirst = plusOne(nextFirst);
        int oldLast = minusOne(nextLast);

        items = (T[]) new Object[target];
        nextFirst = 0;
        nextLast = 1;
        for (int i = oldFirst; i != oldLast; i = plusOne(i, oldItems.length)) {
            items[nextLast] = oldItems[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = oldItems[oldLast];
        nextLast = plusOne(nextLast);
    }

    /**
     * Moves pointer to the prev position.
     * @param index integer, current position.
     * @return the index for the prev position.
     * */
    private int minusOne(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    /**
     * Moves pointer to the next position.
     * @param index integer, current position.
     * @return the index for the next position.
     * */
    private int plusOne(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    /**
     * Moves pointer to the n-step next position.
     * @param index integer, current position.
     * @param offset integer, steps to move.
     * @return the index for the n-step next position.
     * */
    private int plusOffset(int index, int offset) {
        return Math.floorMod(index + offset + 1, items.length);
    }
    /**
     * Moves pointer to the next position.
     * @param index integer, current position.
     * @param length integer, array length.
     * @return the index for the next position.
     * */
    private int plusOne(int index, int length) {
        return Math.floorMod(index + 1, length);
    }
    /**
     * Add an item to the start of the list.
     * @param x: the item to add to the list.
     */
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }
    /**
     * Add an item to the end of the list.
     * @param x: the item to add to the list.
     * */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * factor);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Remove the last item of the list.
     * */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        double usageRatio = (float) size / items.length;
        if (usageRatio < 0.25) {
            resize(items.length / factor);
        }

        nextLast = minusOne(nextLast);
        size -= 1;
        T result = items[nextLast];
        items[nextLast] = null;
        return result;
    }
    /**
     * Remove the first item of the list.
     * */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        double usageRatio = (float) size / items.length;
        if (usageRatio < 0.25) {
            resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        size -= 1;
        T result = items[nextFirst];
        items[nextFirst] = null;
        return result;
    }

    /**
     * Tell if the list is empty or not.
     * @return true if deque is empty, false otherwise.
     * */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the size of the array.
     * @return size of the array.
     */
    public int size() {
        return size;
    }

    /**
     * Print the deque.
     */
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();

    }

    /**
     * Get the item at position index.
     */
    public T get(int index) {
        return items[plusOffset(nextFirst, index)];
    }

}
