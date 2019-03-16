public class Outer {
    private int outvar;

    private class Inner {
        public int invar;

        public Inner() {
            invar = outvar * 2;
        }
    }

    public Outer(int o) {
        outvar = o;
    }

    public void createAndPrintInner() {
        Inner b = new Inner();
        System.out.print(b.invar);
    }

    public static void main(String[] args) {
        Outer a = new Outer(2);
        a.createAndPrintInner();
    }
}
