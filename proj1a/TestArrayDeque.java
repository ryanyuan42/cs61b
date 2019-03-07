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

        assertTrue(list.isEmpty());
        list.addFirst(1);
        list.addFirst(2);
        list.removeFirst();
        list.removeFirst();
        assertTrue(list.isEmpty());

        list.removeFirst();
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

    @Test
    public void testBasicGet(){
        ArrayDeque<Integer> list = new ArrayDeque<Integer>();

        list.addLast(0);
        int actual = list.get(0);
        assertEquals(0, actual);
        list.removeLast();


    }


}
