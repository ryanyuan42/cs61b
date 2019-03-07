public class LinkedListDeque<T> implements Deque<T> {


    private class ItemNode {
        private T first;
        private ItemNode next;
        private ItemNode prev;

        public ItemNode(T f, ItemNode n, ItemNode p) {
            first = f;
            next = n;
            prev = p;
        }
    }

    private int size;
    private ItemNode sentinel;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new ItemNode(item, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new ItemNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printDeque() {
        int n = size;
        ItemNode ptr = sentinel;
        while (n > 0) {
            ptr = ptr.next;
            System.out.print(ptr.first + " ");
            n -= 1;
        }
        System.out.println();
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        ItemNode lastItem = sentinel.prev;
        sentinel.prev = lastItem.prev;
        lastItem.prev.next = sentinel;
        lastItem.prev = null;
        lastItem.next = null;
        size -= 1;
        return lastItem.first;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        ItemNode firstItem = sentinel.next;
        sentinel.next = firstItem.next;
        sentinel.next.prev = sentinel;
        firstItem.next = null;
        firstItem.prev = null;
        size -= 1;
        return firstItem.first;
    }

    @Override
    public T get(int index) {
        ItemNode ptr = sentinel;
        while (index >= 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.first;
    }

    private T getRecursive(ItemNode p, int index) {
        if (index == 0) {
            return p.first;
        }
        return getRecursive(p.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

}
