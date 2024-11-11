import java.util.Scanner;

public class DAA_LA5_JAVA{
    static int N;

    // Function to print the current state of the board
    static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int[][] board, int row, int col) {
        // Check the column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Recursive function to solve the N-Queens problem
    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) // If all queens are placed
            return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place the queen
                System.out.println("Placed queen at (" + row + ", " + col + ")");
                printBoard(board);

                // Recursively place the next queen
                if (solveNQueens(board, row + 1))
                    return true;

                // Backtrack if no safe placement is possible
                board[row][col] = 0;
                System.out.println("Backtracking from (" + row + ", " + col + ")");
                printBoard(board);
            }
        }
        return false; // No safe column found in this row
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        N = scanner.nextInt();
        int[][] board = new int[N][N];

        if (!solveNQueens(board, 0)) {
            System.out.println("No solution exists for N = " + N);
        } else {
            System.out.println("Solution found:");
            printBoard(board);
        }
        scanner.close();
    }
}
