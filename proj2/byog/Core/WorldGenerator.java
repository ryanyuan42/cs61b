package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.SynchronousQueue;


public class WorldGenerator {
    private int WIDTH;
    private int HEIGHT;
    private static final int xSTART = 3;
    private static final int ySTART = 3;
    private TETile[][] world;
    private ArrayList<int[]> wallPosList = new ArrayList<>();


    public WorldGenerator(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        world = new TETile[WIDTH][HEIGHT];

        fillWorldWithNothing();
    }

    public TETile[][] getWorld() {
        return world;
    }

    public void addPerson(int personX, int personY, Person p) {
        world[personX][personY] = p.getElement();
    }

    public void addPerson(Person p) {
        world[p.getxPos()][p.getyPos()] = p.getElement();
    }

    /**
     * Generate a random world
     * Must include rooms and hallway
     */
    public void generate() {
        addFloor(3, 3);
        addFloor(20, 10);
        Room room = new Room(8, 5);
        addRoom(room);
    }


    public void setSeed(long seed) {
        GameObject.setSeed(seed);
    }

    /**
     * @param room: the room to add to the world.
     */
    public void addRoom(Room room) {
        int[] roomPos = room.chooseDoorPos(wallPosList);
        int pointX = roomPos[0];
        int pointY = roomPos[1];
        ArrayList<String> availableSides = new ArrayList<>();
        if (world[xOffset(pointX, -1)][pointY].equals(Tileset.NOTHING)) {
            availableSides.add("r");
        }
        if (world[xOffset(pointX, 1)][pointY].equals(Tileset.NOTHING)) {
            availableSides.add("l");
        }
        if (world[pointX][yOffset(pointY, 1)].equals(Tileset.NOTHING)) {
            availableSides.add("d");
        }
        if (world[pointX][yOffset(pointY, -1)].equals(Tileset.NOTHING)) {
            availableSides.add("u");
        }
        int sideIndex = GameObject.RANDOM.nextInt(availableSides.size());
        String side = availableSides.get(sideIndex);
        switch (side) {
            case "l": room.doorLeft(); break;
            case "r": room.doorRight(); pointX -= room.getXMaxLength() - 1; break;
            case "u": room.doorUp(); pointY -= room.getYMaxLength() - 1; break;
            case "d": room.doorDown(); break;
        }

        int xRest = Math.min(room.getXMaxLength(), WIDTH - pointX);
        int yRest = Math.min(room.getYMaxLength(), HEIGHT - pointY);
        for (int i = 0; i < xRest; i++) {
            for (int j = 0; j < yRest; j++) {
                world[xOffset(i, pointX)][yOffset(j, pointY)] = room.getElement(i, j);
            }
        }
    }

    public void addFloor(int floorX, int floorY) {
        Floor floor = new Floor(40, 40);
        int xRest = Math.min(floor.getXMaxLength(), WIDTH - floorX);
        int yRest = Math.min(floor.getYMaxLength(), HEIGHT - floorY);
        for (int i = 0; i < xRest; i++) {
            for (int j = 0; j < yRest; j++) {
                if (floor.isInside(i, j)) {
                    world[xOffset(i, floorX)][yOffset(j, floorY)] = floor.getElement();
                }
            }
        }

        int pointX;
        int pointY;
        for (int i = 0; i < xRest; i++) {
            for (int j = 0; j < yRest; j++) {
                if (floor.isInside(i, j)) {
                    pointX = xOffset(i, floorX);
                    pointY = yOffset(j, floorY);
                    if (world[xOffset(pointX, -1)][pointY].equals(Tileset.NOTHING)) {
                        world[xOffset(pointX, -1)][pointY] = Tileset.WALL;
                        int[] wallPos = {xOffset(pointX, -1), pointY};
                        wallPosList.add(wallPos);
                    }
                    if (world[xOffset(pointX, 1)][pointY].equals(Tileset.NOTHING)) {
                        world[xOffset(pointX, 1)][pointY] = Tileset.WALL;
                        int[] wallPos = {xOffset(pointX, 1), pointY};
                        wallPosList.add(wallPos);
                    }
                    if (world[pointX][yOffset(pointY, 1)].equals(Tileset.NOTHING)) {
                        world[pointX][yOffset(pointY, 1)] = Tileset.WALL;
                        int[] wallPos = {pointX, yOffset(pointY, 1)};
                        wallPosList.add(wallPos);
                    }
                    if (world[pointX][yOffset(pointY, -1)].equals(Tileset.NOTHING)) {
                        world[pointX][yOffset(pointY, -1)] = Tileset.WALL;
                        int[] wallPos = {pointX, yOffset(pointY, -1)};
                        wallPosList.add(wallPos);
                    }
                }
            }
        }


    }

    private void fillWorldWithNothing() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j  < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private void clearWorldSide() {
        for (int x = 0; x < WIDTH; x++) {
            world[x][0] = Tileset.NOTHING;
            world[x][HEIGHT-1] = Tileset.NOTHING;
        }

        for (int y = 0; y < HEIGHT; y++) {
            world[0][y] = Tileset.NOTHING;
            world[WIDTH-1][y] = Tileset.NOTHING;
        }

    }


    /**
     *
     * @param i, relative position.
     * @param offset, offset amount.
     * @return absolute position.
     */
    public int xOffset(int i, int offset) {
        int xAbsoulte = i + offset;
        if (xAbsoulte >= WIDTH) {
            return WIDTH - 1;
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
        if (yAbsoulte >= HEIGHT) {
            return HEIGHT - 1;
        }
        else if (yAbsoulte < 0) {
            return 0;
        }
        else {
            return yAbsoulte;
        }
    }



    public static void main(String[] args) {
        GameObject.setSeed(1107);
        WorldGenerator worldGenerator = new WorldGenerator(80, 60);
        worldGenerator.generate();
        TETile[][] world = worldGenerator.getWorld();

        TERenderer ter = new TERenderer();
        ter.initialize(80, 60);

        ter.renderFrame(world);
    }
}
