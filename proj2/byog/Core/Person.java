package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import sun.plugin2.message.GetAppletMessage;

import java.awt.event.KeyEvent;

public abstract class Person {
    protected String name;
    protected int health;
    protected int xPos;
    protected int yPos;

    public Person(){
        xPos = 0;
        yPos = 0;
        name = "person";
        health = 100;
    }

    public Person(int x, int y, String n, int h) {
        xPos = x;
        yPos = y;
        name = n;
        health = h;
    }

    public abstract void moveUp(TETile[][] world);
    public abstract void moveDown(TETile[][] world);
    public abstract void moveLeft(TETile[][] world);
    public abstract void moveRight(TETile[][] world);
    public static boolean checkMoveable(TETile[][] world, int x, int y) {
        return world[x][y].equals(Tileset.FLOOR);
    }
}
