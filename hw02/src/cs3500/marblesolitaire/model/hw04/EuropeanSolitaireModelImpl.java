package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the European Solitaire game as a Model.
 */
public class EuropeanSolitaireModelImpl extends MarbleSolitaireModelAbstract {
  /**
   * Constructs a Model with default values.
   */
  public EuropeanSolitaireModelImpl() {
    super();
  }

  /**
   * Constructs a Model with given starting empty cell position.
   *
   * @param sRow starting row of empty cell
   * @param sCol starting col of empty cell
   */
  public EuropeanSolitaireModelImpl(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Constructs a Model with given arm thickness and empty cell at middle.
   *
   * @param armThickness arm thickness of Marble Solitaire game board (positive odd int)
   */
  public EuropeanSolitaireModelImpl(int armThickness) {
    super(armThickness);
  }

  /**
   * Constructs a Model with given arm thickness and empty cell position.
   *
   * @param armThickness arm thickness of Marble Solitaire game board (positive odd int)
   * @param sRow         starting row of empty cell
   * @param sCol         starting col of empty cell
   */
  public EuropeanSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  @Override
  protected boolean invalidPosition(int length, int row, int col) {
    if (row < length - 1 && col < length - 1) {
      return col + row < length - 1;
    } else if (row < length - 1 && col > length * 2 - 2) {
      return col > row + length * 2 - 2;
    } else if (row > length * 2 - 2 && col < length - 1) {
      return row > col + length * 2 - 2;
    } else if (row > length * 2 - 2 && col > length * 2 - 2) {
      return row + col >= length * 5 - 4;
    }
    return super.invalidPosition(length, row, col);
  }

}
