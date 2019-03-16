import javax.swing.text.html.InlineView;

public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public void addLast(int x) {
        IntList p = this;
        while (p.rest != null) {
            p = p.rest;
        }
        p.rest = new IntList(x, null) ;
    }

    /**
     * A = (1,2,3,4,5,6,7,8,9,10)
     * B = (9,8,7,6,5,4,3,2,1)
     * After calling A.skippify(), A: (1,3,6,10)
     * After calling B.skippify(), B: (9,7,4)
     */
    public void skippify() {
        IntList p = this;
        int n = 1;
        while (p != null) {
            IntList next = p.rest;
            for (int i = 0; i < n; i++) {
                if (next == null) {
                    break;
                }
                next = next.rest;
            }
            p.rest = next;
            n += 1;
            p = next;
        }
    }

    public void print() {
        IntList p = this;
        while (p != null) {
            System.out.print(p.first + " ");
            p = p.rest;
        }
        System.out.println();
    }

    /**
     * Given a sorted linked list of items - remove duplicates.
     * For example given (1, 2, 2, 2, 3)
     * Mutate it to become (1, 2, 3) (destructively)
     * @param p
     */
    public static void removeDuplicates(IntList p) {
        if (p == null) {
            return;
        }

        IntList current = p;
        IntList previous = null;
        while (current != null) {
            if (previous == null || current.first != previous.first) {
                previous = current;
            } else {
                previous.rest = current.rest;
            }
            current = current.rest;
        }

    }

    public static void main(String[] args) {
        IntList A = new IntList(1, null);
        for (int i = 2; i <= 10; i++) {
            A.addLast(i);
        }
        A.print();
        A.skippify();
        A.print();

        IntList B = new IntList(9, null);
        for (int i = 8; i >= 1; i--) {
            B.addLast(i);
        }
        B.print();
        B.skippify();
        B.print();

        IntList C = new IntList(1, null);
        C.addLast(2);
        C.addLast(2);
        C.addLast(2);
        C.addLast(3);
        C.print();
        IntList.removeDuplicates(C);
        C.print();
    }
}

