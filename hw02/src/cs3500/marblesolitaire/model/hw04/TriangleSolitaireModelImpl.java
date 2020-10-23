package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the Triangle Solitaire game as a Model.
 */
public class TriangleSolitaireModelImpl extends MarbleSolitaireModelAbstract {
  /**
   * Constructs a Model with default values.
   */
  public TriangleSolitaireModelImpl() {
    this.armThickness = 5;
    this.row = 0;
    this.col = 0;
    this.board = new Cell[5][5];
    this.generateBoard();
  }

  /**
   * Constructs a Model with given starting empty cell position.
   *
   * @param sRow starting row of empty cell
   * @param sCol starting col of empty cell
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    if (invalidPosition(5, sRow, sCol)) {
      throw new IllegalArgumentException();
    }
    this.armThickness = 5;
    this.row = sRow;
    this.col = sCol;
    this.board = new Cell[5][5];
    this.generateBoard();
  }

  /**
   * Constructs a Model with given arm thickness and empty cell at middle.
   *
   * @param armThickness size of triangle Solitaire game board (positive int)
   */
  public TriangleSolitaireModelImpl(int armThickness) {
    if (illegalThickness(armThickness)) {
      throw new IllegalArgumentException();
    }
    this.armThickness = armThickness;
    this.row = 0;
    this.col = 0;
    this.board = new Cell[armThickness][armThickness];
    this.generateBoard();
  }

  /**
   * Constructs a Model with given arm thickness and empty cell position.
   *
   * @param armThickness size of triangle Solitaire game board (positive int)
   * @param sRow         starting row of empty cell
   * @param sCol         starting col of empty cell
   */
  public TriangleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (illegalThickness(armThickness) || invalidPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException();
    }
    this.armThickness = armThickness;
    this.row = sRow;
    this.col = sCol;
    this.board = new Cell[armThickness][armThickness];
    this.generateBoard();
  }

  @Override
  protected void skipOver(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow == toRow && fromCol == toCol + 2) {
      board[fromRow][fromCol - 1] = Cell.empty;
    } else if (fromRow == toRow && fromCol == toCol - 2) {
      board[fromRow][fromCol + 1] = Cell.empty;
    } else if (fromRow == toRow + 2 && fromCol == toCol + 2) {
      board[fromRow - 1][fromCol - 1] = Cell.empty;
    } else if (fromRow == toRow - 2 && fromCol == toCol - 2) {
      board[fromRow + 1][fromCol + 1] = Cell.empty;
    } else if (fromRow == toRow + 2 && fromCol == toCol) {
      board[fromRow - 1][fromCol] = Cell.empty;
    } else if (fromRow == toRow - 2 && fromCol == toCol) {
      board[fromRow + 1][fromCol] = Cell.empty;
    }
  }

  @Override
  protected boolean hasMoves(int row, int col) {
    return !(invalidMove(row, col, row + 2, col + 2)
            && invalidMove(row, col, row - 2, col - 2)
            && invalidMove(row, col, row + 2, col)
            && invalidMove(row, col, row - 2, col)
            && invalidMove(row, col, row, col + 2)
            && invalidMove(row, col, row, col - 2));
  }

  @Override
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
              || (fromCol == toCol + 2 &&
              (fromRow == toRow + 2 && board[fromRow - 1][fromCol - 1].equals(Cell.marble)))
              || (fromCol == toCol - 2 &&
              (fromRow == toRow - 2 && board[fromRow + 1][fromCol + 1].equals(Cell.marble)))
              || ((fromCol == toCol &&
              ((fromRow == toRow + 2 && board[fromRow - 1][fromCol].equals(Cell.marble))
                      || (fromRow == toRow - 2)
                      && board[fromRow + 1][fromCol].equals(Cell.marble)))));
    }
    return check;
  }

  @Override
  protected boolean illegalThickness(int armThickness) {
    return armThickness < 1;
  }

  @Override
  protected boolean invalidPosition(int armThickness, int row, int col) {
    return col > row || row < 0 || col < 0 || row >= armThickness || col >= armThickness;
  }

  @Override
  public String getGameState() {
    String state = "";
    String rowState;
    for (int row = 0; row < board.length; row++) {
      rowState = "";
      for (int i = 0; i < board.length - row - 1; i++) {
        rowState += " ";
      }
      for (Cell c : board[row]) {
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
}
