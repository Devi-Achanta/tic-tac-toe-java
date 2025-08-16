import java.util.*;

public class TicTacToe {
    static String[] board = new String[9];
    static String currentPlayer = "X";

    // All possible winning positions
    static int[][] winPositions = {
        {0,1,2}, {3,4,5}, {6,7,8}, // rows
        {0,3,6}, {1,4,7}, {2,5,8}, // columns
        {0,4,8}, {2,4,6}           // diagonals
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Fill board with numbers
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        System.out.println("Welcome to Tic Tac Toe!");
        printBoard();

        // Step 2: Play until win or draw
        for (int turn = 0; turn < 9; turn++) {
            System.out.print("Player " + currentPlayer + ", choose a slot (1-9): ");
            int slot = sc.nextInt();

            // Check valid slot
            if (slot < 1 || slot > 9) {
                System.out.println("Invalid slot! Try again.");
                turn--; // repeat this turn
                continue;
            }

            // Check if slot already taken
            if (!board[slot - 1].equals(String.valueOf(slot))) {
                System.out.println("Slot already taken! Try again.");
                turn--; // repeat this turn
                continue;
            }

            // Place mark
            board[slot - 1] = currentPlayer;

            // Print updated board
            printBoard();

            // Check for winner
            if (checkWinner()) {
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                return; // End game
            }

            // Switch player
            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }

        // If loop ends without winner → draw
        System.out.println("It's a draw!");
        sc.close();
    }

    // Function to print the board
    static void printBoard() {
        System.out.println("|---|---|---|");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i+1] + " | " + board[i+2] + " |");
            System.out.println("|---|---|---|");
        }
    }

    // Function to check winner
    static boolean checkWinner() {
        for (int[] pos : winPositions) {
            if (board[pos[0]].equals(board[pos[1]]) && board[pos[1]].equals(board[pos[2]])) {
                return true; // Winner found
            }
        }
        return false;
    }
}
