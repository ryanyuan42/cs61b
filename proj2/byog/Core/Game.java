package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.io.*;
import java.util.regex.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

    public void showMainPage() {
        WorldGenerator worldGenerator;
        TETile[][] world;

        drawMainPage();
        if (StdDraw.hasNextKeyTyped()) {
            char c = StdDraw.nextKeyTyped();
            switch (c) {
                case 'n':
                    worldGenerator = new WorldGenerator(80, 40);
                    world = worldGenerator.generate();
                    break;
                case 'N':
                    worldGenerator = new WorldGenerator(80, 40);
                    world = worldGenerator.generate();
                    break;
                case 'q':
                    System.exit(0);
                    break;
                case 'Q':
                    System.exit(0);
                    break;
                case 'l': loadWorld(); break;
                case 'L': ; break;
                default:
            }
        }
    }

    public void drawMainPage() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(0.5, 0.8, "New Game(N)");
        StdDraw.text(0.5, 0.85, "Continue(L)");
        StdDraw.text(0.5, 0.9, "Quit(Q)");
        StdDraw.show();
        StdDraw.pause(100);
    }
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        int worldWidth = 80;
        int worldHeight = 60;
        WorldGenerator worldGenerator;
        TETile[][] world = null;
        boolean start = false;

        while(!start){
            drawMainPage();
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                switch (c) {
                    case 'n':
                        worldGenerator = new WorldGenerator(worldWidth, worldHeight);
                        world = worldGenerator.generate();
                        start = true;
                        break;
                    case 'N':
                        worldGenerator = new WorldGenerator(worldWidth, worldHeight);
                        world = worldGenerator.generate();
                        start = true;
                        break;
                    case 'q':
                        System.exit(0);
                        break;
                    case 'Q':
                        System.exit(0);
                        break;
                    case 'l': ; break;
                    case 'L': ; break;
                    default:
                }
            }
        }

        TERenderer ter = new TERenderer();
        ter.initialize(worldWidth, worldHeight);
        ter.renderFrame(world);

    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        int seed = parseArgument(input);
        WorldGenerator worldGenerator = new WorldGenerator(WIDTH, HEIGHT);
        worldGenerator.setSeed(seed);
        TETile[][] finalWorldFrame = worldGenerator.generate();
        saveWorld(finalWorldFrame);
        return finalWorldFrame;
    }

    public int parseArgument(String input) {
        String patternString = "^N(\\d+)";
        String seedStr = "";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            seedStr = matcher.group(1);
        }
        if (seedStr.equals("")) {
            return 42;
        }
        else {
            return Integer.parseInt(seedStr);
        }
    }

    private static TETile[][] loadWorld() {
        File f = new File("./world.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] loadWorld = (TETile[][]) os.readObject();
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no World has been saved yet, we return a new one. */
        return new TETile[WIDTH][HEIGHT];
    }

    private static void saveWorld(TETile[][] w) {
        File f = new File("./world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(w);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
