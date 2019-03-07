import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDeque {

    @Test
    public void testAddLast(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i ++ ){
            list.addFirst(i);
        }

        /*
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        */


    }
    @Test
    public void testResize(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i ++ ){
            list.addFirst(i);
        }

        for (int i = 0; i < 9; i ++ ){
            list.removeFirst();
            list.printDeque();
        }

        for (int i = 1; i < 15; i++){
            list.addLast(i);
            list.printDeque();
        }
        for (int i = 0; i < 14; i++){
            list.removeFirst();
            list.printDeque();
        }

        list.printDeque();
    }
    @Test
    public void testGetLast(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++){
            list.addLast(i);
        }


        list.removeLast();
        int actual = list.getLast();
        int expected = 98;
        assertEquals(expected, actual);

        ArrayDeque<Integer> list2 = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i ++ ){
            list2.addFirst(i);
        }

        for (int i = 0; i < 9; i ++ ){
            list2.removeFirst();
        }

        for (int i = 1; i < 15; i++){
            list2.addLast(i);
        }
        for (int i = 0; i < 10; i++){
            list2.removeFirst();
        }

        int actual2 = list2.getFirst();
        int expected2 = 10;

        int actual3 = list2.getLast();
        assertEquals(expected2, actual2);
        assertEquals(14, actual3);

    }

    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++){
            list.addFirst(i);
        }

        int actual = list.getLast();
        int expected = 0;
        assertEquals(expected, actual);


    }

    @Test
    public void testRemoveFirst(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++){
            list.addFirst(i);
        }

        int actual = list.removeFirst();

        assertEquals(99, actual);
    }

    @Test
    public void testPrintDeque(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++){
            list.addLast(i);
        }
        System.out.println("Test PrintDeque():");
        list.printDeque();
    }

    @Test
    public void testGet(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++){
            list.addLast(i);
        }

        int actual = list.get(5);
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void testIsEmpty(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();

        boolean actual = list.isEmpty();
        assertTrue(actual);
        list.addFirst(1);
        assertFalse(list.isEmpty());

    }

    @Test
    public void testRemoveLast(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();

        for (int i = 0; i < 100; i++){
            list.addFirst(i);
        }
        int actual = list.removeLast();
        assertEquals(0, actual);

    }

}
