public interface Deque<T> {

    public void addLast(T x);

    public void addFirst(T x);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public T removeFirst();

    public T removeLast();

    public T get(int index);

}
