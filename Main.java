// package TicTacToe;

import java.util.*;

class PlayTicTacToe {
    Scanner sc = new Scanner(System.in);
    char board[][];
    char playerMark, AIMark;

    PlayTicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    void displayBoard() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " | ");
            System.out.println();
            System.out.println("+---+---+---+");
        }
    }

    boolean checkcolWin() {
        for (int j = 0; j < 3; j++)
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ')
                return true;
        return false;
    }

    boolean checkRowWin() {
        for (int i = 0; i < 3; i++)
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ')
                return true;
        return false;
    }

    boolean checkdiagWin() {
        return (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != ' ');
    }

    void placeMark(int row, int column, char mark) {
        try {
            if (row < 0 || row > 2 || column < 0 || column > 2)
                throw new IllegalArgumentException("Invalid position! Row and column must be between 0 and 2.");

            if (board[row][column] != ' ')
                throw new IllegalArgumentException("This position is already taken! Try a different one.");

            board[row][column] = mark;

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void getPlayerMark() {
        System.out.println("Choose your mark from (X/O): ");
        playerMark = sc.nextLine().toUpperCase().charAt(0);

        if (playerMark == 'X')
            AIMark = 'O';
        else if (playerMark == 'O')
            AIMark = 'X';
        else {
            System.out.println("Invalid sign chosen!! Defaulting to X.");
            playerMark = 'X';
            AIMark = 'O';
        }

        System.out.println("You chose : " + playerMark);
    }

    void playerMove() {
        int row, column;
        do {
            System.out.print("Enter row and column (0-2): ");
            row = sc.nextInt();
            column = sc.nextInt();
            System.out.println();
        } while (!isValidMove(row, column));

        placeMark(row, column, playerMark);
    }

    void getAIMove() {
        Random r = new Random();
        int row, col;
        do {
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isValidMove(row, col));

        System.out.println("Computer chose position: " + row + " " + col + "\n");
        placeMark(row, col, AIMark);
    }

    boolean isValidMove(int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == ' ';
    }

    public void play() {
        getPlayerMark();

        for (int turn = 0; turn < 9; turn++) {
            if (turn % 2 == 0) {
                System.out.println("\nYour Turn:");
                playerMove();
            } else {
                System.out.println("\nComputer's Turn:");
                getAIMove();
            }
            displayBoard();

            if (checkRowWin() || checkcolWin() || checkdiagWin()) {
                System.out.println();
                if (turn % 2 == 0) {
                    System.out.println("Congratulations! You outsmarted the computer!");
                } else {
                    System.out.println(
                            "Oops! The computer flexed its virtual muscles this time.");
                }
                return; 
            }
        }

        System.out.println("It's a draw!");
    }
}

public class Main {
    public static void main(String[] args) {
        PlayTicTacToe tic = new PlayTicTacToe();
        tic.play();
    }
}
