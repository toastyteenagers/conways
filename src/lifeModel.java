import java.util.*;
/** 
 * @author: hayden coffing
   purpose: model conways game of life, using an abitrary 
   sized list. 
   */
public class lifeModel {
    private final int BOARD_SIZE = 128;
    private int[][] board;
    
    public lifeModel() {
        generateBoard();
    }
    
    public void addTile(int row, int col) {
        if (row < BOARD_SIZE && col < BOARD_SIZE && row>=0 && col >= 0) {
            board[row][col] = 1;
        }
    }

    public void printBoard() {
        // here is a cute little unicode square character that will represent a live cell: ■
        for (int i = 0; i<BOARD_SIZE; i++) {
            for (int j = 0; j<BOARD_SIZE; j++) {
                String printStr = board[i][j] == 1 ? "■" : " ";
                System.out.print(printStr);
            }
            System.out.println();
        }
    }

    private int[][] copyBoard() {
        int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j<BOARD_SIZE; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }
    
    public void runSimulation1Step() {
        int[][] nextIteration = copyBoard();
        for (int i = 0; i<BOARD_SIZE; i++) {
            int topNeighbor = 0;
            int bottomNeighbor = 0;
            int leftNeighbor = 0; 
            int rightNeighbor = 0; 
            int topRightNeighbor = 0;
            int topLeftNeighbor = 0;
            int bottomRightNeighbor = 0;
            int bottomLeftNeighbor = 0;
            for (int j = 0; j<BOARD_SIZE; j++) {
                // orthogonal neighbors
                if (i != 0) 
                    topNeighbor = board[i-1][j];
                
                if (j != 0)
                    leftNeighbor = board[i][j-1];

                if (i !=BOARD_SIZE-1)
                    bottomNeighbor = board[i+1][j];
                
                if (j != BOARD_SIZE-1)
                    rightNeighbor = leftNeighbor = board[i][j+1];
                
                // diagonal neighbors
                if (i != 0 && j != 0)
                    topLeftNeighbor = board[i-1][j-1];
                
                if (i != 0 && j != BOARD_SIZE-1)
                    topRightNeighbor = board[i-1][j+1];
                
                if (i != BOARD_SIZE-1 && j != 0)
                    bottomLeftNeighbor = board[i+1][j-1];
                
                if (i != BOARD_SIZE-1 && j != BOARD_SIZE-1)
                    bottomRightNeighbor = board[i+1][j+1];
              
                // TODO: maybe add the neighbors in the if statements? This line is really ugly but probably the most readable way to represent what is happening.
                int numNeighbors = topNeighbor + bottomNeighbor + rightNeighbor + leftNeighbor + topRightNeighbor + topLeftNeighbor + bottomLeftNeighbor + bottomRightNeighbor;
                
                /* here are the 3 rules of conways game of life:
                 * 0. any cell has two states, alive, or dead. Any cell has at most 8 neighbors(orthogonal and diagonal)                
                 * 1. any live cell (represetned with a 1) lives if it has two or three live neghbors. 
                 * 2. any dead cell (represetned with a 0) becomes alive if it has 3 neighbors. 
                 * 3. all other cells die
                 */

                int currCell = board[i][j];
                // is there a pithy way of writing this?
                nextIteration[i][j] = 0;
                if (currCell == 1) {
                    // cell is alive 
                    if (numNeighbors == 2 || numNeighbors == 3) {
                        nextIteration[i][j] = 1;
                    }
                } else {
                    // cell is dead
                    if (numNeighbors == 3) {
                        nextIteration[i][j] = 1;
                    }
                }

            }
        }
        board = nextIteration;   
    }
    
    /**
     * Generate the elements of the board, a true for a populated spot, false if not.
     */
    private void generateBoard() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i<BOARD_SIZE; i++) {
            for (int j = 0; j<BOARD_SIZE; j++) {
                board[i][j] = 0; 
            }
        }
    }
}

