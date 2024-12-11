import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY_SLOT = '.';
    private static char[][] board = new char[ROWS][COLUMNS];
    private static char currentPlayer = 'R'; // R = Player 1 (Red), Y = Player 2 (Yellow)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initBoard();
        displayBoard();

        boolean gameRunning = true;
        while (gameRunning) {
            System.out.println("Player " + (currentPlayer == 'R' ? "1 (Red)" : "2 (Yellow)") + ", it's your turn.");
            System.out.print("Enter column (0 to 6): ");
            int column = scanner.nextInt();

            if (makeMove(column)) {
                displayBoard();
                if (checkWinner()) {
                    System.out.println("Player " + (currentPlayer == 'R' ? "1 (Red)" : "2 (Yellow)") + " wins!");
                    gameRunning = false;
                } else if (isBoardFull()) {
                    System.out.println("It's a tie!");
                    gameRunning = false;
                } else {
                    currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R'; // Switch player
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        scanner.close();
    }

    private static void initBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                board[r][c] = EMPTY_SLOT;
            }
        }
    }

    private static void displayBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6"); // Column numbers
    }

    private static boolean makeMove(int column) {
        if (column < 0 || column >= COLUMNS) {
            return false;
        }
        for (int r = ROWS - 1; r >= 0; r--) { // Start from the bottom row
            if (board[r][column] == EMPTY_SLOT) {
                board[r][column] = currentPlayer;
                return true;
            }
        }
        return false; // Column is full
    }

    private static boolean checkWinner() {
        // Check horizontal, vertical, and diagonal wins
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    private static boolean checkHorizontal() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS - 3; c++) {
                if (board[r][c] == currentPlayer &&
                    board[r][c + 1] == currentPlayer &&
                    board[r][c + 2] == currentPlayer &&
                    board[r][c + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVertical() {
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                if (board[r][c] == currentPlayer &&
                    board[r + 1][c] == currentPlayer &&
                    board[r + 2][c] == currentPlayer &&
                    board[r + 3][c] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonal() {
        // Check for diagonal (\)
        for (int r = 0; r < ROWS - 3; r++) {
            for (int c = 0; c < COLUMNS - 3; c++) {
                if (board[r][c] == currentPlayer &&
                    board[r + 1][c + 1] == currentPlayer &&
                    board[r + 2][c + 2] == currentPlayer &&
                    board[r + 3][c + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        // Check for diagonal (/)
        for (int r = 3; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS - 3; c++) {
                if (board[r][c] == currentPlayer &&
                    board[r - 1][c + 1] == currentPlayer &&
                    board[r - 2][c + 2] == currentPlayer &&
                    board[r - 3][c + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int c = 0; c < COLUMNS; c++) {
            if (board[0][c] == EMPTY_SLOT) {
                return false;
            }
        }
        return true;
    }
}
