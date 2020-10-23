package cs3500.marblesolitaire.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

/**
 * Tests for {@link MarbleSolitaireControllerImpl}.
 */
public class MarbleSolitaireControllerImplTest {
  String st = "2 4 4 4 3 6 3 4 q";
  Readable rd = new StringReader(st);
  Appendable ap = new StringBuffer();
  MarbleSolitaireModel model = new MarbleSolitaireModelImpl();
  MarbleSolitaireController cont = new MarbleSolitaireControllerImpl(rd, ap);
  MarbleSolitaireModel triangle = new TriangleSolitaireModelImpl();
  MarbleSolitaireModel european = new EuropeanSolitaireModelImpl();

  @Test
  public void playEuropean() {
    cont.playGame(european);
    String out = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n";
    assertEquals(out, ap.toString());
  }

  @Test
  public void playTriangle() {
    Readable rd = new StringReader("3 1 1 1 3 3 3 1 5 2 3 2 5 4 5 2 5 1 5 3 5 5 3 3 2 2 " +
            "4 4 5 3 3 3 4 1 2 1 4 4 2 2 1 1 3 1 3 1 3 3 3 3 1 1");
    MarbleSolitaireController cont = new MarbleSolitaireControllerImpl(rd, ap);
    cont.playGame(triangle);
    String out = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O _ O O O\n" +
            "Score: 11\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "O O _ _ O\n" +
            "Score: 10\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "_ _ O _ O\n" +
            "Score: 9\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " O _ O _\n" +
            "_ _ O _ _\n" +
            "Score: 8\n" +
            "    O\n" +
            "   _ _\n" +
            "  O O _\n" +
            " O _ O O\n" +
            "_ _ O _ _\n" +
            "Score: 7\n" +
            "    O\n" +
            "   _ _\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "_ _ _ _ _\n" +
            "Score: 6\n" +
            "    O\n" +
            "   O _\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "_ _ _ _ _\n" +
            "Score: 5\n" +
            "    O\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 4\n" +
            "    _\n" +
            "   _ O\n" +
            "  O O _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 3\n" +
            "    _\n" +
            "   _ O\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 2\n" +
            "Game over!\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 1\n";
    assertEquals(out, ap.toString());
  }

  @Test
  public void playGame() {
    cont.playGame(model);
    StringBuffer output = new StringBuffer();
    output.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    output.append("\nScore: " + 32 + "\n");
    output.append("    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    output.append("\nScore: " + 31 + "\n");
    output.append("    O O O\n    O _ O\nO O O O _ _ O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    output.append("\nScore: " + 30 + "\n");
    output.append("Game quit!\nState of game when quit:\n");
    output.append("    O O O\n    O _ O\nO O O O _ _ O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    output.append("\nScore: " + 30 + "\n");
    assertEquals(output.toString(), ap.toString());

    Appendable ap2 = new StringBuffer();
    MarbleSolitaireController cont1 =
            new MarbleSolitaireControllerImpl(new StringReader("1 1 1 1"), ap2);
    cont1.playGame(new MarbleSolitaireModelImpl(1));
    StringBuffer out2 = new StringBuffer("O\nScore: 1\nGame over!\nO\nScore: 1\n");
    assertEquals(out2.toString(), ap2.toString());

    Appendable ap3 = new StringBuffer();
    MarbleSolitaireController cont2 =
            new MarbleSolitaireControllerImpl(new StringReader("1 1 1 1 2 4 4 4 q"), ap3);
    cont2.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out3 = new StringBuffer();
    out3.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out3.append("\nScore: " + 32 + "\n");
    out3.append("Invalid move. Play again.\n");
    out3.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out3.append("\nScore: " + 32 + "\n");
    out3.append("    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out3.append("\nScore: " + 31 + "\n");
    out3.append("Game quit!\nState of game when quit:\n");
    out3.append("    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out3.append("\nScore: " + 31 + "\n");
    assertEquals(out3.toString(), ap3.toString());

    Appendable ap4 = new StringBuffer();
    MarbleSolitaireController cont3 =
            new MarbleSolitaireControllerImpl(new StringReader("a q"), ap4);
    cont3.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out4 = new StringBuffer();
    out4.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out4.append("\nScore: " + 32 + "\n");
    out4.append("Input number " + 1 + " is invalid, try again.\n");
    out4.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out4.append("\nScore: " + 32 + "\n");
    out4.append("Game quit!\nState of game when quit:\n");
    out4.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out4.append("\nScore: " + 32 + "\n");
    assertEquals(out4.toString(), ap4.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void errors1() {
    MarbleSolitaireController cont1 = new MarbleSolitaireControllerImpl(null, ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void errors101() {
    MarbleSolitaireController cont2 = new MarbleSolitaireControllerImpl(rd, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void errors102() {
    MarbleSolitaireController cont3 = new MarbleSolitaireControllerImpl(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void errors103() {
    cont.playGame(null);
  }

  @Test(expected = IllegalStateException.class)
  public void errors2() {
    Appendable ap4 = new StringBuffer();
    MarbleSolitaireController cont3 =
            new MarbleSolitaireControllerImpl(new StringReader("1 1 1 1"), ap4);
    cont3.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out4 = new StringBuffer();
    out4.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out4.append("\nScore: " + 32 + "\n");
    out4.append("Invalid move. Play again.\n");
    out4.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out4.append("\nScore: " + 32 + "\n");
    assertEquals(out4.toString(), ap4.toString());

    Appendable ap5 = new StringBuffer();
    MarbleSolitaireController cont4 =
            new MarbleSolitaireControllerImpl(new StringReader("1 a 1 1 1"), ap5);
    cont4.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out5 = new StringBuffer();
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Input number " + 2 + " is invalid, try again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Invalid move. Play again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    assertEquals(out5.toString(), ap5.toString());
  }

  @Test
  public void playGame01() {
    Appendable ap5 = new StringBuffer();
    MarbleSolitaireController cont4 =
            new MarbleSolitaireControllerImpl(new StringReader("1 1 a 1 1 q"), ap5);
    cont4.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out5 = new StringBuffer();
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Input number " + 3 + " is invalid, try again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Invalid move. Play again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Game quit!\nState of game when quit:\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    assertEquals(out5.toString(), ap5.toString());
  }

  @Test
  public void playGame02() {
    Appendable ap5 = new StringBuffer();
    MarbleSolitaireController cont4 =
            new MarbleSolitaireControllerImpl(new StringReader("a 1 1 1 1 q"), ap5);
    cont4.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out5 = new StringBuffer();
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Input number " + 1 + " is invalid, try again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Invalid move. Play again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Game quit!\nState of game when quit:\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    assertEquals(out5.toString(), ap5.toString());
  }

  @Test
  public void playGame03() {
    Appendable ap5 = new StringBuffer();
    MarbleSolitaireController cont4 =
            new MarbleSolitaireControllerImpl(new StringReader("1 1 1 1 a q"), ap5);
    cont4.playGame(new MarbleSolitaireModelImpl(3));
    StringBuffer out5 = new StringBuffer();
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Invalid move. Play again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Input number " + 1 + " is invalid, try again.\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    out5.append("Game quit!\nState of game when quit:\n");
    out5.append("    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n" +
            "O O O O O O O\n    O O O\n    O O O");
    out5.append("\nScore: " + 32 + "\n");
    assertEquals(out5.toString(), ap5.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void errors201() {
    MarbleSolitaireController cont2 =
            new MarbleSolitaireControllerImpl(new StringReader("1"), ap);
    cont2.playGame(model);
  }

  @Test(expected = IllegalStateException.class)
  public void errors202() {
    MarbleSolitaireController cont2 =
            new MarbleSolitaireControllerImpl(new StringReader("a"), ap);
    cont2.playGame(model);
  }

  @Test(expected = IllegalStateException.class)
  public void errors203() {
    MarbleSolitaireController cont2 =
            new MarbleSolitaireControllerImpl(new StringReader(""), ap);
    cont2.playGame(model);
  }

  @Test(expected = IllegalStateException.class)
  public void errors204() {
    MarbleSolitaireController cont2 =
            new MarbleSolitaireControllerImpl(new StringReader(" "), ap);
    cont2.playGame(model);
  }

  @Test(expected = IllegalStateException.class)
  public void errors2s() {
    cont.playGame(model);
    cont.playGame(model);
  }

  @Test(expected = IllegalStateException.class)
  public void errors3() {
    MarbleSolitaireController cont2 =
            new MarbleSolitaireControllerImpl(new StringReader(" "), new Appendable() {
              @Override
              public Appendable append(CharSequence charSequence) throws IOException {
                throw new IOException();
              }

              @Override
              public Appendable append(CharSequence csq, int start, int end) throws IOException {
                throw new IOException();
              }

              @Override
              public Appendable append(char c) throws IOException {
                throw new IOException();
              }
            });
    cont2.playGame(model);
  }
}