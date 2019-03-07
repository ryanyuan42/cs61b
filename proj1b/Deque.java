public interface Deque<T> {

    /**
     * Add an item to the end of the list.
     * @param x: the item to add to the list.
     * */
    public void addLast(T x);

    public void addFirst(T x);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public T removeFirst();

    public void removeLast();

    public T get(int index);

}
