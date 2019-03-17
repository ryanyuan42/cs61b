package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hexagon {
    /**
     * x: x position of the right corner of the hexagon.
     * y: y position of the right corner of the hexagon.
     * size: size of the hexagon.
     */
    private int sideSize;
    private int xMaxLength;
    private int yMaxLength;
    private boolean mask[][];
    private TETile element;

    public Hexagon(int s, TETile e) {
        sideSize = s;
        element = e;
        xMaxLength = 3 * sideSize - 2;
        yMaxLength = 2 * sideSize;
        mask = new boolean[xMaxLength][yMaxLength];
        generateMask();
    }

    public TETile getElement() {
        return element;
    }

    public boolean isInsideMe(int x, int y) {
        return mask[x][y];
    }

    public int getxMaxLength() {
        return xMaxLength;
    }

    public int getyMaxLength() {
        return yMaxLength;
    }

    private void generateMask() {
        mask = new boolean[xMaxLength][yMaxLength];
        for (int y = 0; y < sideSize; y++) {
            for (int x = 0; x < xMaxLength; x ++) {
                boolean cond = (x > sideSize - 2 - y) & (x < 2 * sideSize - 1 + y);
                mask[x][y] = cond;
            }
        }

        int reverseY = 0;
        for (int y = 2 * sideSize - 1; y > sideSize - 1; y--) {
            for (int x = 0; x < xMaxLength; x ++) {
                boolean cond = (x > sideSize - 2 - reverseY) & (x < 2 * sideSize - 1 + reverseY);
                mask[x][y] = cond;
            }
            reverseY++;
        }

    }

}
