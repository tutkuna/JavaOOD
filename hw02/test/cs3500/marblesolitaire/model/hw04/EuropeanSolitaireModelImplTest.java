package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link EuropeanSolitaireModelImpl}.
 */
public class EuropeanSolitaireModelImplTest {
  MarbleSolitaireModel model = new EuropeanSolitaireModelImpl();
  MarbleSolitaireModel model2 = new EuropeanSolitaireModelImpl(5);
  MarbleSolitaireModel model3 = new EuropeanSolitaireModelImpl(0, 3);
  MarbleSolitaireModel model4 = new EuropeanSolitaireModelImpl(3, 1, 1);

  @Test
  public void testConstruction() {
    String out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    out = "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O";
    assertEquals(out, model2.getGameState());
    out = "    O _ O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model3.getGameState());
    out = "    O O O\n" +
            "  _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model4.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors() {
    MarbleSolitaireModel error = new EuropeanSolitaireModelImpl(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors2() {
    MarbleSolitaireModel error = new EuropeanSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors3() {
    MarbleSolitaireModel error = new EuropeanSolitaireModelImpl(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors4() {
    MarbleSolitaireModel error = new EuropeanSolitaireModelImpl(5, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors5() {
    MarbleSolitaireModel error = new EuropeanSolitaireModelImpl(0, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors4_5() {
    MarbleSolitaireModel error = new EuropeanSolitaireModelImpl(5, 12, 12);
  }

  @Test
  public void testMove_getScore() {
    String out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    assertEquals(36, model.getScore());
    model.move(5, 3, 3, 3);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 1, 4, 3);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 2, 4, 2);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O _ O O O O\n" +
            "O _ O O O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 3, 4, 1);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O _ O O O O\n" +
            "O O _ O O O O\n" +
            "O O _ _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    assertEquals(32, model.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors() {
    model.move(5, 1, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors2() {
    String out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(5, 3, 3, 3);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(6, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors3() {
    String out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(5, 3, 3, 3);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(7, 3, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors4() {
    model.move(0, 4, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors5() {
    String out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(5, 3, 3, 3);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 5, 4, 3);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 4, 4, 4);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "  O O _ O O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(5, 4, 3, 4);
    out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "  O O _ _ O\n" +
            "    O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 4, 2, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors6() {
    model.move(1, 1, 1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors7() {
    model.move(6, 3, 3, 3);
  }

  @Test
  public void testGameOver() {
    assertEquals(false, model.isGameOver());
    assertEquals(true, new EuropeanSolitaireModelImpl(1).isGameOver());
  }
}
