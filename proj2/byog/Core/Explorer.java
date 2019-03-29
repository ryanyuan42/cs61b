package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Explorer extends Person {
    private TETile element = Tileset.PLAYER;

    public Explorer(int x, int y, String n, int h) {
        xPos = x;
        yPos = y;
        name = n;
        health = h;
    }

    public TETile getElement() {
        return element;
    }

    public void setElement(TETile s) {
        element = s;
    }

    public void moveUp(TETile[][] world) {
        int newYPos = yPos + 1;
        if (checkMoveable(world, xPos, newYPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            yPos = newYPos;
            world[xPos][yPos] = getElement();
        }
        updateLocation();
    }

    public void moveDown(TETile[][] world) {
        int newYPos = yPos - 1;
        if (checkMoveable(world, xPos, newYPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            yPos = newYPos;
            world[xPos][yPos] = getElement();
        }
        updateLocation();

    }

    public void moveLeft(TETile[][] world) {
        int newXPos = xPos - 1;
        if (checkMoveable(world, newXPos, yPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            xPos = newXPos;
            world[xPos][yPos] = getElement();
        }
        updateLocation();

    }

    public void moveRight(TETile[][] world) {
        int newXPos = xPos + 1;
        if (checkMoveable(world, newXPos, yPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            xPos = newXPos;
            world[xPos][yPos] = getElement();
        }
        updateLocation();
    }
}
