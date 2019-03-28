package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.regex.*;
import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;
    private WorldGenerator worldGenerator = new WorldGenerator(WIDTH, HEIGHT);
    private long SEED;

    public void drawMainPage() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(0.5, 0.8, "New Game(N)");
        StdDraw.text(0.5, 0.85, "Continue(L)");
        StdDraw.text(0.5, 0.9, "Quit(Q)");
        StdDraw.show();
        StdDraw.pause(100);
    }

    public void showUserTypeText() {
        StdDraw.text(0.5, 0.75, "Random seed: ");
        StdDraw.show();
    }

    public int userInputSeed() {
        double curPos = 0.45;
        String seedStr = "";

        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                break;
            }

            if (StdDraw.hasNextKeyTyped()) {

                String c = Character.toString(StdDraw.nextKeyTyped());
                seedStr += c;
                curPos += 0.03;
                StdDraw.text(curPos, 0.65, c);
            }
        }
        return seedStr.equals("") ? 42: Integer.parseInt(seedStr);
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        WorldGenerator worldGenerator;
        TETile[][] world = null;
        boolean start = false;

        while(!start){
            drawMainPage();
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toLowerCase(StdDraw.nextKeyTyped());
                switch (c) {
                    case 'n':
                        showUserTypeText();
                        SEED = userInputSeed();
                        GameObject.setSeed(SEED);
                        worldGenerator = new WorldGenerator(WIDTH, HEIGHT);
                        world = worldGenerator.generate();
                        start = true;
                        break;
                    case 'q':
                        System.exit(0);
                        break;
                    case 'l':
                        world = loadWorld();
                        start = true;
                        break;
                    default: break;
                }
            }
        }

        TERenderer ter = new TERenderer();

        int[] startPos = pickStart(world);
        int startX = startPos[0];
        int startY = startPos[1];
        Explorer ryan = new Explorer(startX, startY, "ryan", 100);
        world[startX][startY] = ryan.getElement();

        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) & StdDraw.isKeyPressed(KeyEvent.VK_SEMICOLON)) {
                String command = listenCommand();
                switch (command) {
                    case ":s": saveWorld(); break;
                    case ":q": quit(); break;
                    default: break;
                }
            }

            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toLowerCase(StdDraw.nextKeyTyped());
                switch (c) {
                    case 'w' : ryan.moveUp(world); break;
                    case 'a' : ryan.moveLeft(world); break;
                    case 's' : ryan.moveDown(world); break;
                    case 'd' : ryan.moveRight(world); break;
                    default: break;
                }
            }

            ter.renderFrame(world);
            StdDraw.pause(5);
        }
    }

    public void quit() {
        System.exit(0);
    }

    public String listenCommand() {
        String command = "";
        String showCommand;
        double x = 0.5;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                showCommand = String.valueOf(c);
                command += showCommand;
                drawCommand(x, showCommand);
                x += 0.7;
                StdDraw.pause(100);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                break;
            }
        }
        return command;
    }

    public void drawCommand(double x, String s) {
        Font font = new Font("Arial", Font.PLAIN, 16);
        StdDraw.setPenColor(Color.yellow);
        StdDraw.setFont(font);

        StdDraw.text(x, HEIGHT - 5, s);
        StdDraw.show();
    }

    public int[] pickStart(TETile[][] world) {
        int x = 40;
        int y = 40;

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j].equals(Tileset.FLOOR)) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        int[] start = {x, y};
        return start;
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
        TETile[][] finalWorldFrame =  parseArgument(input);
        return finalWorldFrame;
    }

    public TETile[][] parseArgument(String input) {
        int n = input.length();
        char cur;
        TETile[][] world = null;
        String seed = "";
        int seedNum;

        for (int i = 0; i < n; i++) {
            cur = Character.toLowerCase(input.charAt(i));
            switch (cur) {
                case 'n':
                    while (i < n - 1) {
                        char c = input.charAt(i+1);
                        if (Character.isDigit(c)) {
                            seed += c;
                        } else {
                            break;
                        }
                        i += 1;
                    }
                    SEED = Long.parseLong(seed);
                    worldGenerator.setSeed(SEED);
                    world = worldGenerator.generate();
                    break;
                case 'l': world = loadWorld(); break;
                case 's':
                    saveWorld();
                    break;
                case 'q': System.exit(0); break;
            }
        }
        return world;
    }

    private TETile[][] loadWorld() {
        File f = new File("./world.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                SEED = (long) os.readObject();
                worldGenerator.setSeed(SEED);
                TETile[][] world = worldGenerator.generate();
                os.close();
                return world;
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

    private void saveWorld() {
        File f = new File("./world.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(SEED);
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
