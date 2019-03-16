public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        IntNode cur = first;
        IntNode prev = null;
        if (position == 0 || first == null) {
            addFirst(item);
            return;
        }

        while (position > 0 && cur != null) {
            prev = cur;
            cur = cur.next;
            position -= 1;
        }
        prev.next = new IntNode(item, cur);

    }

    public void reverse() {
        IntNode frontOfReversed = null;
        IntNode nextNodeToAdd = first;
        while (nextNodeToAdd != null) {
            IntNode remainderOfReverse = nextNodeToAdd.next;
            nextNodeToAdd.next = frontOfReversed;
            frontOfReversed = nextNodeToAdd;
            nextNodeToAdd = remainderOfReverse;
        }
        first = frontOfReversed;
    }

    public void reverseRecur() {
        first = reverseRecur(first);
    }

    // 3 2 1 0
    public IntNode reverseRecur(IntNode front) {
        if (front == null || front.next == null) {
            return front;
        }
        IntNode reversed = reverseRecur(front.next);
        front.next.next = front;
        front.next = null;

        return reversed;
    }


    public void print() {
        IntNode cur = first;
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        SLList list = new SLList();
        list.addFirst(0);
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.reverseRecur();
        list.print();

    }


}


