package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState current;
        private int moves;
        private SearchNode prev;
        private int disToGoal;

        public SearchNode(WorldState c, int m, SearchNode p) {
            current = c;
            moves = m;
            prev = p;
            disToGoal = c.estimatedDistanceToGoal();
        }

        public int moves() {
            return moves;
        }

        public int disToGoal() {
            return disToGoal;
        }

        public WorldState curState() {
            return current;
        }

        public SearchNode preNode() {
            return prev;
        }

        @Override
        public int compareTo(SearchNode sn) {
            return moves() + disToGoal() - (sn.moves() - sn.disToGoal());
        }
    }

    private SearchNode goalNode;
    private ArrayDeque<WorldState> solution = new ArrayDeque<>();

    public Solver(WorldState initial){
        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode start = new SearchNode(initial, 0, null);
        pq.insert(start);

        while (!pq.isEmpty()) {
            SearchNode X = pq.delMin();
            if (X.curState().isGoal()) {
                goalNode = X;
                return;
            }
            for (WorldState neighbor : X.curState().neighbors()) {
                if (X.preNode() == null || !neighbor.equals(X.preNode().curState())) {
                    SearchNode newNode = new SearchNode(neighbor, X.moves() + 1, X);
                    pq.insert(newNode);
                }
            }
        }
        SearchNode current = goalNode;
        while (current != null) {
            solution.addFirst(current.curState());
            current = current.preNode();
        }
    }

    public int moves(){
        return goalNode.moves();
    }
    public Iterable<WorldState> solution(){
        return solution;
    }
}
