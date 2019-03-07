public class LinkedListDeque<T> implements Deque<T> {


    public class ItemNode {
        public T first;
        public ItemNode next;
        public ItemNode prev;

        public ItemNode(T f, ItemNode n, ItemNode p) {
            first = f;
            next = n;
            prev = p;
        }
    }

    public int size;
    public ItemNode sentinel;
    // public ItemNode last;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = new ItemNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item){
        sentinel.next.prev = new ItemNode(item, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    public void addLast(T item){
        sentinel.prev.next = new ItemNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        int n = size;
        ItemNode ptr = sentinel;
        while (n > 0) {
            ptr = ptr.next;
            System.out.println(ptr.first);
            n -= 1;
        }
    }

    public T removeLast(){
        ItemNode last_item = sentinel.prev;
        last_item.prev.next = sentinel;
        last_item.prev = null;
        last_item.next = null;
        size -= 1;
        return last_item.first;
    }

    public T removeFirst(){
        ItemNode first_item = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        first_item.next = null;
        first_item.prev = null;
        size -= 1;
        return first_item.first;
    }

    public T get(int index) {
        ItemNode ptr = sentinel;
        while (index >= 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.first;
    }

    private T getRecursive(ItemNode p, int index){
        if (index == 0){
            return p.first;
        }
        return getRecursive(p.next, index - 1);
    }
    public T getRecursive(int index){
        return getRecursive(sentinel.next, index);
    }

}
