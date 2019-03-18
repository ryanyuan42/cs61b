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


    public WorldGenerator(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        world = new TETile[WIDTH][HEIGHT];

        fillWorldWithNothing();
    }

    /**
     * Generate a random world
     * Must include rooms and hallway
     */
    public TETile[][] generate() {
        addFloor(3, 3);
        addFloor(20, 10);
        return world;
    }


    public void setSeed(int seed) {
        GameObject.setSeed(seed);
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
                    }
                    if (world[xOffset(pointX, 1)][pointY].equals(Tileset.NOTHING)) {
                        world[xOffset(pointX, 1)][pointY] = Tileset.WALL;
                    }
                    if (world[pointX][yOffset(pointY, 1)].equals(Tileset.NOTHING)) {
                        world[pointX][yOffset(pointY, 1)] = Tileset.WALL;
                    }
                    if (world[pointX][yOffset(pointY, -1)].equals(Tileset.NOTHING)) {
                        world[pointX][yOffset(pointY, -1)] = Tileset.WALL;
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
        TETile[][] world = worldGenerator.generate();

        TERenderer ter = new TERenderer();
        ter.initialize(80, 60);

        ter.renderFrame(world);
    }
}
