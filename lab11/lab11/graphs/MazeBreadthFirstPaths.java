package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        bfsHelper(s);
    }

    private void bfsHelper(int v) {
        marked[v] = true;
        announce();
        if (v == t) {
            targetFound = true;
        }
        if (targetFound) {
            return;
        }
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(v);
        while (!q.isEmpty()) {
            v = q.dequeue();
            for (int i : maze.adj(v)) {
                if (!marked[i]) {
                    edgeTo[i] = v;
                    distTo[i] = distTo[v] + 1;
                    marked[i] = true;
                    announce();
                    q.enqueue(i);
                    if (i == t) {
                        targetFound = true;
                    }
                    if (targetFound) {
                        return;
                    }
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

