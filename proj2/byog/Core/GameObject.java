package byog.Core;

import byog.TileEngine.TETile;

import java.util.Random;

public abstract class GameObject {
    protected static Random RANDOM = new Random(42);
    protected int xPos;
    protected int yPos;
    protected int xMaxLength;
    protected int yMaxLength;

    public GameObject() {
        xPos = 0;
        yPos = 0;
        xMaxLength = 40;
        yMaxLength = 40;
    }

    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    public GameObject(int w, int h) {
        xPos = 0;
        yPos = 0;
        xMaxLength = w;
        yMaxLength = h;
    }

    public abstract TETile getElement();

    public int getXMaxLength() {
        return xMaxLength;
    }

    public int getYMaxLength() {
        return yMaxLength;
    }


    /**
     *
     * @param i, relative position.
     * @param offset, offset amount.
     * @return absolute position.
     */
    public int xOffset(int i, int offset) {
        int xAbsoulte = i + offset;
        if (xAbsoulte >= xMaxLength) {
            return xMaxLength - 1;
        }
        else if (xAbsoulte < 0) {
            return 0;
        }
        else {
            return xAbsoulte;
        }
    }

    /**
     *
     * @param i, relative position.
     * @param offset, offset amount.
     * @return absolute position.
     */
    public int yOffset(int i, int offset) {
        int yAbsoulte = i + offset;
        if (yAbsoulte >= yMaxLength) {
            return yMaxLength - 1;
        }
        else if (yAbsoulte < 0) {
            return 0;
        }
        else {
            return yAbsoulte;
        }
    }
}
