import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    private ArrayDequeSolution<String> messageQueue = new ArrayDequeSolution<>();
    public String getMessge(){
        String msg = "";
        int size = messageQueue.size();
        for (int i = 0; i < size; i++){
            msg = msg + " " + messageQueue.get(size - 1 - i);
        }
        return msg;
    }
    @Test
    public void testStudentDeque(){
        StudentArrayDeque<Integer> studentArrayDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();

        Integer studentResult;
        Integer solutionResult;


        for (int i = 0; i < 10; i++){
            Integer a = StdRandom.uniform(1, 100);
            studentArrayDeque.addFirst(a);
            arrayDequeSolution.addFirst(a);
            messageQueue.addLast("addFirst(" + a + ")");
        }



        for (int i = 0; i < 5; i++){
            studentResult =  studentArrayDeque.removeFirst();
            solutionResult = arrayDequeSolution.removeFirst();
            messageQueue.addLast("removeFirst()");
            assertEquals(getMessge(),
                         solutionResult, studentResult);

        }

        for (int i = 0; i < 5; i++){
            studentResult = studentArrayDeque.removeLast();
            solutionResult = arrayDequeSolution.removeLast();
            messageQueue.addLast("removeLast()");
            assertEquals(getMessge(),
                        solutionResult, studentResult);


        }


    }

}
