package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.*;
import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 60;
    private WorldGenerator worldGenerator = new WorldGenerator(WIDTH, HEIGHT);
    private long SEED;
    private ArrayList<Location> personLocationList = new ArrayList<Location>();

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
    }

    public int userInputSeed() {
        double curPos = 0.45;
        String seedStr = "";

        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                break;
            }
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c == KeyEvent.VK_BACK_SPACE) {
                    if (!seedStr.equals("")){
                        seedStr = seedStr.substring(0, seedStr.length() - 1);
                    }
                }
                else {
                    seedStr += c;
                }
                StdDraw.setPenColor(Color.black);
                StdDraw.filledRectangle(curPos, 0.65, 1, 0.05);
                StdDraw.setPenColor(Color.yellow);
                StdDraw.textLeft(curPos, 0.65, seedStr);

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
        personLocationList.add(ryan.getLocation());
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
        double x = 0.5;
        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
                break;
            }

            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c == KeyEvent.VK_BACK_SPACE) {
                    if (!command.equals("")){
                        command = command.substring(0, command.length() - 1);
                        drawCommand(x, command);
                    } else{
                        break;
                    }
                } else{
                    command += c;
                    drawCommand(x, command);
                }

                StdDraw.pause(100);
            }

        }
        return command;
    }

    public void drawCommand(double x, String s) {
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(x, HEIGHT - 2, 10, 5);
        Font font = new Font("Arial", Font.PLAIN, 16);
        StdDraw.setPenColor(Color.yellow);
        StdDraw.setFont(font);

        StdDraw.textLeft(x, HEIGHT - 2, s);
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
                personLocationList = (ArrayList<Location>) os.readObject();
                worldGenerator.setSeed(SEED);
                TETile[][] world = worldGenerator.generate();
                for (Location location : personLocationList) {
                    world[location.getxPos()][location.getyPos()] = Tileset.PLAYER;
                }
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
            os.writeObject(personLocationList);
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
