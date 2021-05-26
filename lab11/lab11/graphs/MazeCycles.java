package lab11.graphs;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(maze.V());


    }

    // Helper methods go here
}

