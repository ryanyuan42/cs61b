package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Explorer extends Person {

    public Explorer(int x, int y, String n, int h) {
        xPos = x;
        yPos = y;
        name = n;
        health = h;
    }

    public Explorer(PersonData d) {
        xPos = d.getxPos();
        yPos = d.getyPos();
        name = d.getName();
        health = d.getHealth();
    }

    public void moveUp(TETile[][] world) {
        int newYPos = yPos + 1;
        if (checkMoveable(world, xPos, newYPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            yPos = newYPos;
            world[xPos][yPos] = getElement();
        }
        updateData();
    }

    public void moveDown(TETile[][] world) {
        int newYPos = yPos - 1;
        if (checkMoveable(world, xPos, newYPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            yPos = newYPos;
            world[xPos][yPos] = getElement();
        }
        updateData();

    }

    public void moveLeft(TETile[][] world) {
        int newXPos = xPos - 1;
        if (checkMoveable(world, newXPos, yPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            xPos = newXPos;
            world[xPos][yPos] = getElement();
        }
        updateData();
    }

    public void moveRight(TETile[][] world) {
        int newXPos = xPos + 1;
        if (checkMoveable(world, newXPos, yPos)) {
            world[xPos][yPos] = Tileset.FLOOR;
            xPos = newXPos;
            world[xPos][yPos] = getElement();
        }
        updateData();
    }
}
