package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
        s = 1;
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        dfs(s);
    }

    // Helper methods go here
    private void dfs(int v) {
        if (cycleFound) {
            return;
        }
        marked[v] = true;
        announce();
        for (int i : maze.adj(v)) {
            if (!marked[i]) {
                edgeTo[i] = v;
                dfs(i);
                if (cycleFound) {
                    return;
                }
            } else if (marked[i] && i != edgeTo[v]) {  //i is not the parent of v
                edgeTo[i] = v;
                cycleFound = true;
                announce();
                return;
            }
        }
    }
}

