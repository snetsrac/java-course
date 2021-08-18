import java.util.Arrays;
import java.util.Scanner;

public class Battleship {
  private static final int NUM_SHIPS = 5;
  private static final int MAX_ROWS = 5;
  private static final int MAX_COLS = 5;
  private static final char EMPTY = '-';
  private static final char SHIP = '@';
  private static final char HIT = 'X';
  private static final char MISS = 'O';
  private static final String INVALID_COORDINATES = "Invalid coordinates. Choose different coordinates.";
  private static final String SHIP_ALREADY_THERE = "You already have a ship there. Choose different coordinates.";
  private static final String ALREADY_FIRED_THERE = "You already fired on this spot. Choose different coordinates.";
  
  private static Scanner input;
  private static char[][] player1LocationBoard;
  private static char[][] player2LocationBoard;
  private static char[][] player1HistoryBoard;
  private static char[][] player2HistoryBoard;

  public static void main(String[] args) {
    input = new Scanner(System.in);

    System.out.println("Welcome to Battleship!\n");

    System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
    player1LocationBoard = setupLocationBoard();
    printBattleShip(player1LocationBoard);
    print100Lines();

    System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
    player2LocationBoard = setupLocationBoard();
    printBattleShip(player2LocationBoard);
    print100Lines();

    player1HistoryBoard = initBoard();
    player2HistoryBoard = initBoard();

    while (true) {
      takeTurn(1, 2);
      if (allShipsDestroyed(player2LocationBoard)) {
        System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
        break;
      }

      takeTurn(2, 1);
      if (allShipsDestroyed(player1LocationBoard)) {
        System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
        break;
      }
    }

    System.out.println("Final boards:\n");
    printBattleShip(player1LocationBoard);
    System.out.println();
    printBattleShip(player2LocationBoard);
  }

  private static char[][] setupLocationBoard() {
    char[][] board = initBoard();

    for (int i = 1; i <= NUM_SHIPS; i++) {
      int[] coords = getCoordinates(board, "Enter ship " + i + " location:", true);
      board[coords[0]][coords[1]] = SHIP;
    }

    return board;
  }

  private static char[][] initBoard() {
    char[][] board = new char[MAX_ROWS][MAX_COLS];
    for (char[] row : board) {
      Arrays.fill(row, EMPTY);
    }
    return board;
  }

  private static int[] getCoordinates(char[][] board, String prompt, boolean isSetup) {
    boolean coordinatesAreValid = false;
    int row, col;

    do {
      System.out.println(prompt);

      row = input.nextInt();
      col = input.nextInt();

      if (row < 0 || row >= MAX_ROWS || col < 0 || col >= MAX_COLS) {
        System.out.println(INVALID_COORDINATES);
        continue;
      }

      if (isSetup && board[row][col] != EMPTY) {
        System.out.println(SHIP_ALREADY_THERE);
        continue;
      }

      if (!isSetup && board[row][col] != EMPTY) {
        System.out.println(ALREADY_FIRED_THERE);
        continue;
      }

      coordinatesAreValid = true;        
    } while (!coordinatesAreValid);

    int[] coords = new int[2];
    coords[0] = row;
    coords[1] = col;
    return coords;
  }

  private static void print100Lines() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
  }

  private static void takeTurn(int playerId, int targetId) {
    char[][] playerHistoryBoard;
    char[][] targetLocationBoard;

    if (playerId == 1) {
      playerHistoryBoard = player1HistoryBoard;
      targetLocationBoard = player2LocationBoard;
    } else {
      playerHistoryBoard = player2HistoryBoard;
      targetLocationBoard = player1LocationBoard;
    }

    int[] coords = getCoordinates(playerHistoryBoard, "Player " + playerId + ", enter hit row/column:", false);
    int row = coords[0];
    int col = coords[1];

    if (targetLocationBoard[row][col] == SHIP) {
      System.out.println("PLAYER " + playerId + " HIT PLAYER " + targetId + "'s SHIP!");
      playerHistoryBoard[row][col] = HIT;
      targetLocationBoard[row][col] = HIT;
    } else {
      System.out.println("PLAYER " + playerId + " MISSED!");
      playerHistoryBoard[row][col] = MISS;
      targetLocationBoard[row][col] = MISS;
    }

    printBattleShip(playerHistoryBoard);
    System.out.println();
  }

  private static boolean allShipsDestroyed(char[][] board) {
    for (char[] row : board) {
      for (char element : row) {
        if (element == SHIP) return false;
      }
    }
    return true;
  }

  // Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}
