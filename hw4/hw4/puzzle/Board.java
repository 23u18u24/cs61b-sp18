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
        int mistakeC = 0;
        int expectedV = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != expectedV) {
                    mistakeC++;
                }
                expectedV++;
            }
        }
        return mistakeC;
    }
    /* Manhattan estimate described below */
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int actualV = tileAt(i, j);
                if (actualV == 0) {
                    continue;
                }
                int expectedRow = (actualV - 1) / N;
                int expectedCol = (actualV - 1) % N;
                distance = Math.abs(expectedCol - j) + Math.abs(expectedRow - i);
            }
        }
        return distance;
    }
    /* Estimated distance to goal. This method should simply return
    the results of manhattan() when submitted to Gradescope.*/
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    /* Returns true if this board's tile values are the same
       position as y's*/
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board otherBoard = (Board) y;
        if (N != otherBoard.N) {
            return false;
        }
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                if (this.tileAt(r, c) != otherBoard.tileAt(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }
    /* Returns the string representation of the board. This
       method is provided in the skeleton*/
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
