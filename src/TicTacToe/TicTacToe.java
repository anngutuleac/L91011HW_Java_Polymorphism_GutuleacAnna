package TicTacToe;
import java.util.Scanner;

public class TicTacToe {
    private char[] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[9];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
            System.out.println("-------------");
        }
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int move;

        while (!isGameOver()) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;

            if (isValidMove(move)) {
                makeMove(move);
                displayBoard();

                if (currentPlayerWins()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    return;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println("It's a tie!");
    }

    private boolean isValidMove(int move) {
        return move >= 0 && move < 9 && board[move] == ' ';
    }

    private void makeMove(int move) {
        board[move] = currentPlayer;
    }

    private boolean currentPlayerWins() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == currentPlayer && board[i + 1] == currentPlayer && board[i + 2] == currentPlayer) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i] == currentPlayer && board[i + 3] == currentPlayer && board[i + 6] == currentPlayer) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0] == currentPlayer && board[4] == currentPlayer && board[8] == currentPlayer) ||
                (board[2] == currentPlayer && board[4] == currentPlayer && board[6] == currentPlayer)) {
            return true;
        }

        return false;
    }

    private boolean isGameOver() {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.displayBoard();
        game.playGame();
    }
}