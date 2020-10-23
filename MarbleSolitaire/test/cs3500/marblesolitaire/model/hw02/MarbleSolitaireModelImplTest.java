package cs3500.marblesolitaire.model.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link MarbleSolitaireModelImpl}.
 */
public class MarbleSolitaireModelImplTest {
  MarbleSolitaireModel model1 = new MarbleSolitaireModelImpl();
  MarbleSolitaireModel model2 = new MarbleSolitaireModelImpl(5);
  MarbleSolitaireModel model3 = new MarbleSolitaireModelImpl(0, 4);
  MarbleSolitaireModel model4 = new MarbleSolitaireModelImpl(3, 3, 3);
  MarbleSolitaireModel model5 = new MarbleSolitaireModelImpl(1);

  @Test(expected = IllegalArgumentException.class)
  public void constructionErrors() {
    MarbleSolitaireModel error1 = new MarbleSolitaireModelImpl(0);
    MarbleSolitaireModel error2 = new MarbleSolitaireModelImpl(4);
    MarbleSolitaireModel error3 = new MarbleSolitaireModelImpl(3, 0, 0);
    MarbleSolitaireModel error4 = new MarbleSolitaireModelImpl(0, 0);
    MarbleSolitaireModel error5 = new MarbleSolitaireModelImpl(0, 3, 3);
  }

  @Test
  public void testConstruction() {
    String expected0 = "        O O O O O\n        O O O O O\n        O O O O O" +
            "\n        O O O O O\nO O O O O O O O O O O O O\nO O O O O O O O O O O O O" +
            "\nO O O O O O _ O O O O O O\nO O O O O O O O O O O O O\nO O O O O O O O O O O O O\n" +
            "        O O O O O\n        O O O O O\n        O O O O O\n        O O O O O";
    assertEquals(expected0, model2.getGameState());
    assertEquals("O", model5.getGameState());
    String expected1 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected1, model4.getGameState());
    MarbleSolitaireModel model10 = new MarbleSolitaireModelImpl(3);
    assertEquals(expected1,model10.getGameState());
  }

  @Test
  public void move() {
    String expected0 = "    O O _\n    O O O\nO O O O O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected0, model3.getGameState());
    String expected1 = "    _ _ O\n    O O O\nO O O O O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    model3.move(0, 2, 0, 4);
    assertEquals(expected1, model3.getGameState());

    model3 = new MarbleSolitaireModelImpl(0, 4);

    String expected2 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected2, model1.getGameState());
    model1.move(1, 3, 3, 3);
    String expected3 = "    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected3, model1.getGameState());

    model1 = new MarbleSolitaireModelImpl();
    expected2 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected2, model1.getGameState());
    model1.move(5, 3, 3, 3);
    expected3 = "    O O O\n    O O O\nO O O O O O O\nO O O O O O O\n" +
            "O O O _ O O O\n    O _ O\n    O O O";
    assertEquals(expected3, model1.getGameState());

    model1 = new MarbleSolitaireModelImpl();

    expected2 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected2, model1.getGameState());
    model1.move(3, 5, 3, 3);
    expected3 = "    O O O\n    O O O\nO O O O O O O\nO O O O _ _ O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected3, model1.getGameState());

  }

  @Test(expected = IllegalArgumentException.class)
  public void moveErrors() {
    model1.move(3, 5, 3, 3);
    model1.move(3,6,3,4);
    String expected = "    O O _\n    O O O\nO O O O O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    model1.move(0, 0, 0, 0);
    model3 = new MarbleSolitaireModelImpl(0, 4);
    model3.move(0, 4, 0, 2);
    assertEquals(expected, model3.getGameState());
    model4.move(3, 0, 0, 0);
  }

  @Test
  public void isGameOver() {
    assertEquals(false, model1.isGameOver());
    assertEquals(true, model5.isGameOver());
  }

  @Test
  public void getGameState() {
    model1 = new MarbleSolitaireModelImpl();
    String expected1 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O";
    assertEquals(expected1, model1.getGameState());
  }

  @Test
  public void getScore() {
    assertEquals(32, model1.getScore());
    assertEquals(1, model5.getScore());
  }
}
