package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int BLANK = 0;
    private int[][] tiles;
    private int N;
    /* Constructs a board from an N-by-N array of tiles where
       tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    /* Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (i > N - 1 || i < 0 || j > N - 1 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    /* Returns the board size N */
    public int size() {
        return N;
    }
    /* Returns the neighbors of the current board */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    /* Hamming estimate described below */
    public int hamming() {
        return -1;
    }
    /* Manhattan estimate described below */
    public int manhattan() {
        return -1;
    }
    /* Estimated distance to goal. This method should simply return
    the results of manhattan() when submitted to Gradescope.*/
    public int estimatedDistanceToGoal() {
        return -1;
    }
    /* Returns true if this board's tile values are the same
       position as y's*/
    public boolean equals(Object y) {
        return true;
    }
    /* Returns the string representation of the board. This
       method is provided in the skeleton*/
    public String toString() {
        return "";
    }
}
