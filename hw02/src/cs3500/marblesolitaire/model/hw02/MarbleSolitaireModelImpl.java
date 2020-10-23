package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelAbstract;

/**
 * Represents the Marble Solitaire game as a Model.
 */
public class MarbleSolitaireModelImpl extends MarbleSolitaireModelAbstract {
  /**
   * Constructs a Model with default values.
   */
  public MarbleSolitaireModelImpl() {
    super();
  }

  /**
   * Constructs a Model with given starting empty cell position.
   *
   * @param sRow starting row of empty cell
   * @param sCol starting col of empty cell
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    super(sRow, sCol);
  }

  /**
   * Constructs a Model with given arm thickness and empty cell at middle.
   *
   * @param armThickness arm thickness of Marble Solitaire game board (positive odd int)
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    super(armThickness);
  }

  /**
   * Constructs a Model with given arm thickness and empty cell position.
   *
   * @param armThickness arm thickness of Marble Solitaire game board (positive odd int)
   * @param sRow         starting row of empty cell
   * @param sCol         starting col of empty cell
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

}
