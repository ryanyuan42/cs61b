package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Arrays;


public class Floor extends GameObject{
    private boolean[][] floor;

    public Floor(int w, int h) {
        super(w, h);
        floor = new boolean[xMaxLength][yMaxLength];
        generatePath();
    }

    public TETile getElement() {
        return Tileset.FLOOR;
    }

    public boolean isInside(int x, int y) {
        return floor[x][y];
    }

    private void generatePath() {
        int curX = xPos;
        int curY = yPos;
        int count = 0;
        int chooseNumber = 0;
        long maxUpdateTimes = Math.round(xMaxLength * yMaxLength * 0.25);
        int[] curPos;
        int[] nullPos = {-1, -1};

        while (curX >= 0 | curY >= 0){
            int[] endPosDown = generateDownPath(curX, curY);
            int[] endPosUp = generateUpPath(curX, curY);
            int[] endPosLeft = generateLeftPath(curX, curY);
            int[] endPosRight = generateRightPath(curX, curY);

            if (count % 5 == 0){
                chooseNumber = RANDOM.nextInt(100);
            }
            switch (chooseNumber % 10){
                case 0: curPos = endPosDown; break;
                case 1: curPos = endPosDown; break;
                case 2: curPos = endPosRight; break;
                case 3: curPos = endPosRight; break;
                case 4: curPos = endPosRight; break;
                case 5: curPos = endPosLeft; break;
                case 6: curPos = endPosLeft; break;
                case 7: curPos = endPosUp; break;
                case 8: curPos = endPosUp; break;
                case 9: curPos = endPosUp; break;
                default: curPos = endPosUp;
            }
            if (!Arrays.equals(curPos, nullPos)) {
                curX = curPos[0];
                curY = curPos[1];
            }

            count += 1;
            if (count > maxUpdateTimes) {
                break;
            }

        }
    }


    private int[] generateDownPath(int x, int y) {
        int a = RANDOM.nextInt(500);
        int[] endPos = {-1, -1};
        if (a > 2) {
            int nextYPos = yOffset(y, -1);

            floor[x][nextYPos] = true;
            endPos[0] = x;
            endPos[1] = nextYPos;
            return endPos;
        }
        else {
            return endPos;

        }
    }

    private int[] generateUpPath(int x, int y) {
        int a = RANDOM.nextInt(500);
        int[] endPos = {-1, -1};
        if (a > 3) {
            int nextYPos = yOffset(y, 1);

            floor[x][nextYPos] = true;
            endPos[0] = x;
            endPos[1] = nextYPos;
            return endPos;
        }
        else {
            return endPos;

        }

    }

    private int[] generateLeftPath(int x, int y) {
        int a = RANDOM.nextInt(500);
        int[] endPos = {-1, -1};
        if (a > 8) {
            int nextXPos = xOffset(x, -1);

            floor[nextXPos][y] = true;
            endPos[0] = nextXPos;
            endPos[1] = y;
            return endPos;
        }
        else {
            return endPos;

        }
    }

    private int[] generateRightPath(int x, int y) {
        int a = RANDOM.nextInt(500);
        int[] endPos = {-1, -1};
        if (a > 2) {
            int nextXPos = xOffset(x, 1);

            floor[nextXPos][y] = true;
            endPos[0] = nextXPos;
            endPos[1] = y;
            return endPos;
        }
        else {
            return endPos;

        }
    }


}
