import org.junit.Test;
import static org.junit.Assert.*;
/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
    @Test
    public void testAddFirst() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
        list.printDeque();

        for (int i = 0; i < 10; i++) {
            list.removeLast();
        }

        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveFirst() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }
        list.printDeque();

        for (int i = 0; i < 10; i++) {
            list.removeFirst();
        }

        assertTrue(list.isEmpty());
    }

    @Test
    public void testSize() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }

        for (int i = 10; i < 80; i++) {
            list.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            list.removeLast();
            list.removeFirst();
        }

        assertEquals(40, list.size());
    }

    @Test
    public void testIterativeGet() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }

        for (int i = 10; i < 80; i++) {
            list.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            list.removeLast();
            list.removeFirst();
        }

        int actual = list.get(0);
        assertEquals(20, actual);
    }

    @Test
    public void testRecursiveGet() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }

        for (int i = 10; i < 80; i++) {
            list.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            list.removeLast();
            list.removeFirst();
        }

        int actual = list.getRecursive(9);
        assertEquals(29, actual);
    }

    @Test
    public void testNegativeSize() {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            list.addFirst(i);
        }

        for (int i = 10; i < 20; i++) {
            list.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            list.removeLast();
            list.removeFirst();
        }

        assertTrue(list.isEmpty());
    }

}
