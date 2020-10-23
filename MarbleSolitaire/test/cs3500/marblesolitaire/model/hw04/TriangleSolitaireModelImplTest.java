package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link TriangleSolitaireModelImpl}.
 */
public class TriangleSolitaireModelImplTest {
  MarbleSolitaireModel model = new TriangleSolitaireModelImpl();
  MarbleSolitaireModel model2 = new TriangleSolitaireModelImpl(13);
  MarbleSolitaireModel model3 = new TriangleSolitaireModelImpl(3, 2, 0);
  MarbleSolitaireModel model4 = new TriangleSolitaireModelImpl(6, 5, 0);
  MarbleSolitaireModel model5 = new TriangleSolitaireModelImpl(3, 0);

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors() {
    MarbleSolitaireModel error1 = new TriangleSolitaireModelImpl(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors2() {
    MarbleSolitaireModel error2 = new TriangleSolitaireModelImpl(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors3() {
    MarbleSolitaireModel error205 = new TriangleSolitaireModelImpl(3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors4() {
    MarbleSolitaireModel error3 = new TriangleSolitaireModelImpl(0, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors5() {
    MarbleSolitaireModel error4 = new TriangleSolitaireModelImpl(5, 3, 4);
  }

  @Test
  public void testConstruction() {
    String out = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    String out2 = "            _\n" +
            "           O O\n" +
            "          O O O\n" +
            "         O O O O\n" +
            "        O O O O O\n" +
            "       O O O O O O\n" +
            "      O O O O O O O\n" +
            "     O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "   O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            " O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O";
    assertEquals(out2, model2.getGameState());
    String out3 = "  O\n" +
            " O O\n" +
            "_ O O";
    assertEquals(out3, model3.getGameState());
    String out4 = "     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "_ O O O O O";
    assertEquals(out4, model4.getGameState());
    String out5 = "    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "O O O O O";
    assertEquals(out5, model5.getGameState());
  }

  @Test
  public void test_move_isGameOver_getScore() {
    String out = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    assertEquals(14, model.getScore());
    model.move(2, 0, 0, 0);
    out = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 2, 2, 0);
    out = "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 1, 2, 1);
    out = "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O _ O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 3, 4, 1);
    out = "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O O _ _ O";
    assertEquals(out, model.getGameState());
    model.move(4, 0, 4, 2);
    out = "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "_ _ O _ O";
    assertEquals(out, model.getGameState());
    model.move(4, 4, 2, 2);
    out = "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O _ O _\n" +
            "_ _ O _ _";
    assertEquals(out, model.getGameState());
    model.move(1, 1, 3, 3);
    out = "    O\n" +
            "   _ _\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "_ _ O _ _";
    assertEquals(out, model.getGameState());
    model.move(4, 2, 2, 2);
    out = "    O\n" +
            "   _ _\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "_ _ _ _ _";
    assertEquals(out, model.getGameState());
    model.move(3, 0, 1, 0);
    out = "    O\n" +
            "   O _\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "_ _ _ _ _";
    assertEquals(out, model.getGameState());
    model.move(3, 3, 1, 1);
    out = "    O\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _";
    assertEquals(out, model.getGameState());
    model.move(0, 0, 2, 0);
    out = "    _\n" +
            "   _ O\n" +
            "  O O _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _";
    assertEquals(out, model.getGameState());
    model.move(2, 0, 2, 2);
    out = "    _\n" +
            "   _ O\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _";
    assertEquals(out, model.getGameState());
    assertEquals(false, model.isGameOver());
    assertEquals(2, model.getScore());
    model.move(2, 2, 0, 0);
    out = "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _";
    assertEquals(out, model.getGameState());
    assertEquals(true, model.isGameOver());
    assertEquals(1, model.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors() {
    model2.move(0, 3, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors2() {
    model2.move(2, 2, 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors3() {
    model.move(2, 0, 0, 0);
    String out = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 2, 2, 0);
    out = "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 1, 2, 1);
    out = "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O _ O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 3, 4, 1);
    out = "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O O _ _ O";
    assertEquals(out, model.getGameState());
    model.move(4, 2, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors4() {
    model2.move(3, 3, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors5() {
    model.move(2, 0, 0, 0);
    String out = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 2, 2, 0);
    out = "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 1, 2, 1);
    out = "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O _ O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 1, 4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors6() {
    model2.move(3, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors7() {
    model.move(2, 0, 0, 0);
    String out = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(2, 2, 2, 0);
    out = "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(out, model.getGameState());
    model.move(4, 0, 2, 2);
  }
}