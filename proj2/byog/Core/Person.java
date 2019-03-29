package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.io.*;

public abstract class Person{
    protected String name;
    protected double health;
    protected int xPos;
    protected int yPos;
    protected Location location = new Location();
    protected TETile element = Tileset.PLAYER;
    protected PersonData personData;

    public void updateData() {
        personData.setxPos(xPos);
        personData.setyPos(yPos);
        personData.setHealth(health);
        personData.setName(name);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public Location getLocation() {
        return location;
    }

    public Person(){
        xPos = 0;
        yPos = 0;
        name = "person";
        health = 100;
        personData = new PersonData(xPos, yPos, name, health);
    }




    public PersonData getPersonData() {
        return personData;
    }

    public void setxPos(int x) {
        xPos = x;
    }

    public void setyPos(int y) {
        yPos = y;
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
    public TETile getElement() {
        return element;
    }

    public void setElement(TETile s) {
        element = s;
    }

    public static boolean checkMoveable(TETile[][] world, int x, int y) {
        return world[x][y].equals(Tileset.FLOOR) | world[x][y].equals(Tileset.UNLOCKED_DOOR);
    }
}
