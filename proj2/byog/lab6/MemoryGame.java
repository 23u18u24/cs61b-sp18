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
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
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
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuffer end = new StringBuffer();
        for (int i = 0; i < n; i++) {
            end.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return end.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear();
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width / 2, height/2, s);
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.textLeft(1,height-1,"Round: " + round);
        StdDraw.text(width/2,height-1,"Watch!");
        StdDraw.textRight(width-1,height-1,ENCOURAGEMENT[new Random(ENCOURAGEMENT.length).nextInt()]);
        StdDraw.line(0,height-2,width,height-2);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            StdDraw.clear();
            StdDraw.pause(500);
            StdDraw.text(width/2, height/2, letters);
            StdDraw.show();
            StdDraw.pause(750);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < n; i++) {
            if (StdDraw.hasNextKeyTyped()) {
                s.append(StdDraw.nextKeyTyped());
            }
            drawFrame(s.toString());
        }
        return s.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        //TODO: Establish Game loop
        while (true) {
            StdDraw.clear();
            StdDraw.text(width/2,height/2,"Round: " + round);
            String num = generateRandomString(round);
            flashSequence(num);
            String result = solicitNCharsInput(round);
            if (!num.equals(result)) {
                System.out.println("Game Over! You made it to round:" + round);
                return;
            }
            round += 1;
        }
    }

}
