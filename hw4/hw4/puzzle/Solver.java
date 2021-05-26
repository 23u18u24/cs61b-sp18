package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;

public class Solver {
    private final MinPQ<SearchNode> pq;
    private SearchNode result;

    private class SearchNode {
        private WorldState ws;
        private int count;
        private SearchNode prevNode;
        public SearchNode(WorldState ws, int count, SearchNode prevNode) {
            this.ws = ws;
            this.count = count;
            this.prevNode = prevNode;
        }
    }
    /* Constructor which solves the puzzle, computing everything
    necessary for moves() and solution() to not have to solve the
    problem again. Solves the puzzle using the A* algorithm. Assumes a
    solution exists. */
    public Solver(WorldState initial) {
        pq = new MinPQ<SearchNode>();
        SearchNode ini = new SearchNode(initial, 0, null);
        pq.insert(ini);
        solverHelper(pq.delMin());
    }

    private void solverHelper(SearchNode X) {
        if (X.ws.isGoal()) {
            result = X;
            return;
        }
        X.count = X.prevNode.count + 1;
        for (WorldState w : X.ws.neighbors()) {
            SearchNode n = new SearchNode(w,  X.count + w.estimatedDistanceToGoal(), X);
            if (w == X.prevNode.ws) {
                continue;
            } else {
                pq.insert(n);
            }
            if (n.ws.isGoal()) {
                result = n;
                return;
            }
        }
        solverHelper(pq.delMin());
    }

    /* Returns the minimum number of moves to solve the puzzle
    starting at the initial WorldState. */
    public int moves() {
        return result.count;
    }

    /*  Returns a sequence of WorldStates from the initial WorldState
    to the solution.*/
    public Iterable<WorldState> solution() {
        ArrayList<WorldState> a = new ArrayList<>(result.count);
        while (result.prevNode != null) {
            a.add(result.prevNode.ws);
            result = result.prevNode;
        }
        return a;
    }
}
