package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static int WIDTH;
    private static int HEIGHT;
    private TETile[][] world;

    public HexWorld(int w, int h) {
        WIDTH = w;
        HEIGHT = h;
        world = new TETile[WIDTH][HEIGHT];
        fillWorldWithNothing();
    }

    private void fillWorldWithNothing() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j  < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] getWorld() {
        return world;
    }



    public void printHexagon(boolean[][] hexagon) {
        for (int y = 0; y < hexagon[0].length; y++) {
            for (int x = 0; x < hexagon.length; x++) {
                System.out.print(hexagon[x][y]);
            }
            System.out.println();
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
        return (xAbsoulte >= WIDTH) ? WIDTH - 1: xAbsoulte;
    }

    /**
     *
     * @param i, relative position.
     * @param offset, offset amount.
     * @return absolute position.
     */
    public int yOffset(int i, int offset) {
        int yAbsoulte = i + offset;
        return (yAbsoulte >= HEIGHT) ? HEIGHT - 1: yAbsoulte;
    }

    /**
     * add a hexagon to the world, sit at x and y
     * @param x: x position of right corner of the hexagon.
     * @param y: y position of right corner of the hexagon.
     */
    public void addHexagon(int x, int y, Hexagon hexagon) {

        int xRest = Math.min(hexagon.getxMaxLength(), WIDTH - x);
        int yRest = Math.min(hexagon.getyMaxLength(), HEIGHT - y);
        for (int i = 0; i < xRest; i++) {
            for (int j = 0; j < yRest; j++) {
                if (hexagon.isInsideMe(i, j)) {
                    world[xOffset(i, x)][yOffset(j, y)] = hexagon.getElement();
                }
            }
        }
    }



    public static void main(String[] args) {
        HexWorld world = new HexWorld(60, 30);

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        Hexagon hexagon = new Hexagon(5, Tileset.TREE);
        Hexagon hexagon2 = new Hexagon(6, Tileset.FLOWER);
        Hexagon hexagon3 = new Hexagon(7, Tileset.WALL);
        Hexagon hexagon4 = new Hexagon(4, Tileset.WATER);
        Hexagon hexagon5 = new Hexagon(5, Tileset.LOCKED_DOOR);

        world.addHexagon(0, 10, hexagon);
        world.addHexagon(5,  20, hexagon2);
        world.addHexagon(10, 5, hexagon3);
        world.addHexagon(15, 20, hexagon4);
        world.addHexagon(20, 15, hexagon5);

        ter.renderFrame(world.getWorld());
    }
}
