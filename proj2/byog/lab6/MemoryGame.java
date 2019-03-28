package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.rand.setSeed(seed);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand = new Random();
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        int i;
        String randomString = "";
        while (n > 0){
            i = rand.nextInt(26);
            randomString += CHARACTERS[i];
            n -= 1;
        }
        return randomString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.yellow);
        StdDraw.setFont(font);
        double x = width / 2;
        double y = width / 2;

        StdDraw.text(x, y, s);
        StdDraw.show();


    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters

        String c;
        int strLength = letters.length();
        for (int i = 0; i < strLength; i++) {
            c = Character.toString(letters.charAt(i));
            drawFrame(c);
            StdDraw.pause(1000);
            drawFrame(" ");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String result = "";
        while (result.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                result += String.valueOf(c);
                drawFrame(result);
            }
        }
        StdDraw.pause(500);
        return result;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts

        //TODO: Establish Game loop
        round = 1;

        while (!gameOver) {
            drawFrame("Round: " + round);
            StdDraw.pause(1000);
            String s = generateRandomString(round);
            flashSequence(s);
            drawFrame(ENCOURAGEMENT[rand.nextInt(7)]);
            StdDraw.pause(1000);
            String userInput = solicitNCharsInput(round);
            if (userInput.equals(s)) {
                round += 1;
                drawFrame("Congratulation! Starting Round: " + round);
                StdDraw.pause(1000);
            } else {
                drawFrame("GAME OVER, You made it to round: " + round);
                gameOver = true;
                break;
            }
        }
    }
}
