package byog.Core;

import java.io.Serializable;

public class PersonData implements Serializable {
    private static final long serialVersionUID = 135243643120935L;
    private int xPos;
    private int yPos;
    private String name;
    private double health;

    public PersonData(int x, int y, String s, double h) {
        xPos = x;
        yPos = y;
        name = s;
        health = h;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public double getHealth() {
        return health;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public String getName() {
        return name;
    }
}
