package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This abstract class represents common behaviors of all implementations of MarbleSolitaireModel.
 */
public abstract class MarbleSolitaireModelAbstract implements MarbleSolitaireModel {
  protected enum Cell {
    out, marble, empty
  }

  protected int armThickness;
  protected int row;
  protected int col;
  protected Cell[][] board;

  /**
   * Constructs a Model with default values.
   */
  public MarbleSolitaireModelAbstract() {
    this.armThickness = 3;
    this.row = 3;
    this.col = 3;
    this.board = new Cell[7][7];
    this.generateBoard();
  }

  /**
   * Constructs a Model with given starting empty cell position.
   *
   * @param sRow starting row of empty cell
   * @param sCol starting col of empty cell
   */
  public MarbleSolitaireModelAbstract(int sRow, int sCol) {
    if (invalidPosition(3, sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + ", " + sCol + ")");
    }
    this.armThickness = 3;
    this.row = sRow;
    this.col = sCol;
    this.board = new Cell[7][7];
    this.generateBoard();
  }

  /**
   * Constructs a Model with given arm thickness and empty cell at middle.
   *
   * @param armThickness arm thickness of Marble Solitaire game board
   */
  public MarbleSolitaireModelAbstract(int armThickness) {
    if (illegalThickness(armThickness)) {
      throw new IllegalArgumentException();
    }
    this.armThickness = armThickness;
    this.row = armThickness + armThickness / 4;
    this.col = armThickness + armThickness / 4;
    this.board = new Cell[armThickness * 3 - 2][armThickness * 3 - 2];
    this.generateBoard();
  }

  /**
   * Constructs a Model with given arm thickness and empty cell position.
   *
   * @param armThickness arm thickness of Marble Solitaire game board
   * @param sRow         starting row of empty cell
   * @param sCol         starting col of empty cell
   */
  public MarbleSolitaireModelAbstract(int armThickness, int sRow, int sCol) {
    if (illegalThickness(armThickness) || invalidPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException();
    }
    this.armThickness = armThickness;
    this.row = sRow;
    this.col = sCol;
    this.board = new Cell[armThickness * 3 - 2][armThickness * 3 - 2];
    this.generateBoard();
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (invalidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException();
    } else {
      board[fromRow][fromCol] = Cell.empty;
      board[toRow][toCol] = Cell.marble;
      this.skipOver(fromRow, fromCol, toRow, toCol);
    }
  }

  @Override
  public boolean isGameOver() {
    boolean done = true;
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col].equals(Cell.marble) && hasMoves(row, col)) {
          done = false;
        }
      }
    }
    return done;
  }

  @Override
  public String getGameState() {
    String state = "";
    String rowState = "";
    for (Cell[] row : board) {
      rowState = "";
      for (Cell c : row) {
        if (c.equals(Cell.empty)) {
          rowState += "_";
        } else if (c.equals(Cell.marble)) {
          rowState += "O";
        } else if (c.equals(Cell.out)) {
          rowState += " ";
        }
        rowState += " ";
      }
      int index = Math.max(rowState.lastIndexOf("O") + 1, rowState.lastIndexOf("_") + 1);
      state += rowState.substring(0, index) + "\n";
    }
    state = state.substring(0, state.length() - 1);
    return state;
  }

  @Override
  public int getScore() {
    int score = 0;
    for (Cell[] row : board) {
      for (Cell c : row) {
        if (c.equals(Cell.marble)) {
          score++;
        }
      }
    }
    return score;
  }

  protected boolean illegalThickness(int armThickness) {
    return armThickness % 2 != 1;
  }

  protected boolean invalidPosition(int length, int row, int col) {
    return row < 0 || col < 0 || row >= length * 3 - 2 || col >= length * 3 - 2
            || (row < length - 1 && col < length - 1)
            || (row < length - 1 && col > length * 2 - 2)
            || (row > length * 2 - 2 && col < length - 1)
            || (row > length * 2 - 2 && col > length * 2 - 2);
  }

  protected void skipOver(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow && fromCol == toCol + 2) {
      board[fromRow][fromCol - 1] = Cell.empty;
    } else if (fromRow == toRow && fromCol == toCol - 2) {
      board[fromRow][fromCol + 1] = Cell.empty;
    } else if (fromRow == toRow + 2 && fromCol == toCol) {
      board[fromRow - 1][fromCol] = Cell.empty;
    } else if (fromRow == toRow - 2 && fromCol == toCol) {
      board[fromRow + 1][fromCol] = Cell.empty;
    }
  }

  protected boolean invalidMove(int fromRow, int fromCol, int toRow, int toCol) {
    boolean check = invalidPosition(this.armThickness, fromRow, fromCol)
            || invalidPosition(this.armThickness, toRow, toCol);
    if (!check) {
      check = !board[fromRow][fromCol].equals(Cell.marble) ||
              !board[toRow][toCol].equals(Cell.empty);
    }
    if (!check) {
      check = !((fromRow == toRow &&
              ((fromCol == toCol + 2 && board[fromRow][fromCol - 1].equals(Cell.marble))
                      || (fromCol == toCol - 2
                      && board[fromRow][fromCol + 1].equals(Cell.marble))))
              || ((fromCol == toCol &&
              ((fromRow == toRow + 2 && board[fromRow - 1][fromCol].equals(Cell.marble))
                      || (fromRow == toRow - 2)
                      && board[fromRow + 1][fromCol].equals(Cell.marble)))));
    }
    return check;
  }

  protected boolean hasMoves(int row, int col) {
    return !(invalidMove(row, col, row + 2, col)
            && invalidMove(row, col, row - 2, col)
            && invalidMove(row, col, row, col + 2)
            && invalidMove(row, col, row, col - 2));
  }

  protected void generateBoard() {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        if (invalidPosition(this.armThickness, row, col)) {
          board[row][col] = Cell.out;
        } else if (row == this.row && col == this.col) {
          board[row][col] = Cell.empty;
        } else {
          board[row][col] = Cell.marble;
        }
      }
    }
  }
}
