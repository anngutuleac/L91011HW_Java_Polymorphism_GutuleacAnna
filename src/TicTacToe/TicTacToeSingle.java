package TicTacToe;

import java.util.Scanner;

public class TicTacToeSingle {
    private char[] board;
    private char player;
    private char currentPlayer;
    private char computer;

    public TicTacToeSingle(char player) {
        board = new char[9];
        this.player = player;
        computer = (player == 'X') ? 'O' : 'X';
        currentPlayer = (player == 'X') ? player : computer;
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
            if (currentPlayer == player) {
                // Player's turn
                System.out.println("Player's turn.");
                System.out.print("Enter your move (1-9): ");
                move = scanner.nextInt() - 1;

                if (isValidMove(move)) {
                    makeMove(move, player);
                    displayBoard();

                    if (currentPlayerWins(player)) {
                        System.out.println("Player wins!");
                        return;
                    }
                    swapCurrentPlayer();
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                // Computer's turn
                System.out.println("Computer's turn.");
                move = getComputerMove();

                makeMove(move, computer);
                displayBoard();

                if (currentPlayerWins(computer)) {
                    System.out.println("Computer wins!");
                    return;
                }
                swapCurrentPlayer();
            }
        }

        System.out.println("It's a tie!");
    }

    private void swapCurrentPlayer() {
        currentPlayer = (currentPlayer == player) ? computer : player;
    }

    private boolean isValidMove(int move) {
        return move >= 0 && move < 9 && board[move] == ' ';
    }

    private void makeMove(int move, char currentPlayer) {
        board[move] = currentPlayer;
    }

    private boolean currentPlayerWins(char currentPlayer) {
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

    private int getComputerMove() {
        // Try to win if possible
        for (int i = 0; i < 9; i++) {
            if (isValidMove(i)) {
                makeMove(i, computer);
                if (currentPlayerWins(computer)) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }

        // Try to block player if they can win
        for (int i = 0; i < 9; i++) {
            if (isValidMove(i)) {
                makeMove(i, player);
                if (currentPlayerWins(player)) {
                    board[i] = ' ';
                    return i;
                }
                board[i] = ' ';
            }
        }

        // Choose a random move
        int move;
        do {
            move = (int) (Math.random() * 9);
        } while (!isValidMove(move));

        return move;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select your player (X or O): ");
        char player = scanner.next().charAt(0);

        if (player != 'X' && player != 'O') {
            System.out.println("Invalid player selection. Exiting the game.");
        } else {
            TicTacToeSingle game = new TicTacToeSingle(player);
            game.displayBoard();
            game.playGame();
        }
    }
}