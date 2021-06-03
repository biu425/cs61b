package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int t;
    private int s;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here.
        // Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(s);
        marked[s] = true;
        announce();

        while (!q.isEmpty()) {
            int item = q.poll();
            for (int j : maze.adj(item)) {
                if (!marked[j]) {
                    marked[j] = true;
                    edgeTo[j] = item;
                    distTo[j] = distTo[item] + 1;
                    announce();
                    if (j == t) {
                        return;
                    } else {
                        q.add(j);
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

