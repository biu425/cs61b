package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] perc;
    //private boolean[] perc1D;
    private int size;
    private int openNum = 0;
    private WeightedQuickUnionUF connectedGrids;
    private WeightedQuickUnionUF gridsNoBottom;
    private int top;
    private int bottom;


    private int xyIndexTo1D(int row, int col) {
        return row * size + col + 1;
    }

    //create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        perc = new boolean[N][N];
        size = N;

        //virtual top and bottom
        top = 0;
        bottom = size * size + 1;
//        perc1D = new boolean[N * N];
//        for (int i = 0; i < perc1D.length; i++) {
//            perc1D[i] = false;
//        }
        connectedGrids = new WeightedQuickUnionUF(N * N + 2);
        gridsNoBottom = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < size; i++) {
            connectedGrids.union(xyIndexTo1D(0, i), top);
            gridsNoBottom.union(xyIndexTo1D(0, i), top);
            connectedGrids.union(xyIndexTo1D(size - 1, i), bottom);
        }
    }

    private void validIndex(int row, int col) {
        if (row >= size || col >= size || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    //open the site(row, col) if it is not open already
    public void open(int row, int col) {
        validIndex(row, col);
        //int indexIn1D = xyIndexTo1D(row, col);
        if (!isOpen(row, col)) {
            perc[row][col] = true;
            openNum++;
        }
        //connect with neighbors
        connectionUpdated(row - 1, col, row, col); //left
        connectionUpdated(row + 1, col, row, col); //right
        connectionUpdated(row, col - 1, row, col); //up
        connectionUpdated(row, col + 1, row, col); //down

    }

    private void connectionUpdated(int rowN, int colN, int row, int col) {
        validIndex(row, col);
        int indexIn1D = xyIndexTo1D(row, col);
        int indexIn1DN = xyIndexTo1D(rowN, colN);
        if (rowN >= 0 && rowN < size && colN >= 0 && colN < size) {
            if (isOpen(rowN, colN)) {
                connectedGrids.union(indexIn1D, indexIn1DN);
                gridsNoBottom.union(indexIn1D, indexIn1DN);
            }
        }
    }

    //is the site(row, col) open?
    public boolean isOpen(int row, int col) {
        validIndex(row, col);
        return perc[row][col];
    }

    //is the site(row,col) full?
    //connected with the top?
    public boolean isFull(int row, int col) {
        validIndex(row, col);
        if (isOpen(row, col)) {
            int indexIn1D = xyIndexTo1D(row, col);
            return gridsNoBottom.connected(indexIn1D, top);
        } else {
            return false;
        }
    }

    //number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }

    //does the system percolate?
    public boolean percolates() {
        if (size != 1) {
            return connectedGrids.connected(top, bottom);
        } else {
            return isFull(0, 0);
        }
    }

    //use for unit testing(not required)
    public static void main(String[] args) {
    }
}
