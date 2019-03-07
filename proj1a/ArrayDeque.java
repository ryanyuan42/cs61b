public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int Factor = 2;

    /**
     * Creates an empty array deque.
     * */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Resize the array list to the capacity.
     * @param target: integer, the length of the new array.
     * */
    private void resize(int target) {
        // TODO: refactor resize to smaller functions.
        T[] oldItems = items;
        int oldFirst = plusOne(nextFirst);
        int oldLast = minusOne(nextLast);

        items = (T[]) new Object[target];
        nextFirst = 4;
        nextLast = 5;
        for (int i = oldFirst; i != oldLast; i = plusOne(i, oldItems.length)) {
            items[nextLast] = oldItems[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = oldItems[oldLast];
        nextLast = plusOne(nextLast);
    }

    private int minusOne(int index) {
        return Math.floorMod(index - 1, items.length);
    }

    private int plusOne(int index) {
        return Math.floorMod(index + 1, items.length);
    }

    private int plusOffset(int index, int offset){
        return Math.floorMod(index + offset + 1, items.length);
    }

    private int plusOne(int index, int size) {
        return Math.floorMod(index + 1, size);
    }
    /**
     * Add an item to the start of the list
     * @param x: the item to add to the list.
     */
    @Override
    public void addFirst(T x){
        if (size == items.length){
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
    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * Factor);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Remove the last item of the list.
     * */
    @Override
    public T removeLast(){
        double usage_ratio = (float)size / items.length;
        if (usage_ratio < 0.25) {
            resize(items.length / Factor);
        }

        nextLast = minusOne(nextLast);
        size -= 1;
        return items[nextLast];
    }
    /**
     * Remove the first item of the list.
     * */
    @Override
    public T removeFirst(){
        double usage_ratio = (float)size / items.length;
        if (usage_ratio < 0.25) {
            resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        size -= 1;
        return items[nextFirst];
    }

    /**
     * Get the last item of the list.
     * */

    public T getLast() {
        return items[nextLast - 1];
    }

    /**
     * Get the last item of the list.
     * */
    public T getFirst() {
        return items[nextFirst + 1];
    }

    /**
     * Tell if the list is empty or not
     * */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Get the size of the array
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * Print the deque.
     */
    @Override
    public void printDeque(){
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)){
            System.out.print(items[i] + " ");
        }
        System.out.println();

    }

    /**
     * Get the item at position index.
     */
    @Override
    public T get(int index){
        return items[plusOffset(nextFirst, index)];
    }

}
