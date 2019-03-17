package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


public class WorldGenerator {
    private long SEED;
    private Random RANDOM;

    public WorldGenerator(int s) {
        SEED = s;
        RANDOM = new Random(SEED);
    }

    /**
     * Generate a random world
     */
    public void generate() {


    }
}
