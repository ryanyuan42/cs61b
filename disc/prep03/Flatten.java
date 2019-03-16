import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Flatten {
    /**
     * flatten({{1,2,3}, {}, {7, 8}} should return {1,2,3,7,8}
    */
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int i = 0; i < x.length; i++) {
            totalLength += x[i].length;
        }

        int [] a = new int[totalLength];
        int aIndex = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                a[aIndex] = x[i][j];
                aIndex += 1;
            }
        }
        return a;
    }

    public static void printList(int[] list) {
        for (int a : list) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] list = {{1,2,3}, {}, {7,8}};
        int[] fList = flatten(list);
    }

}
