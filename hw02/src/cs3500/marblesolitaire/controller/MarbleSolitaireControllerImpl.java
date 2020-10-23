package cs3500.marblesolitaire.controller;


import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the controller for the marble solitaire game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private Scanner in;
  private Appendable out;

  /**
   * Constructs a controller with given Readable input and Appendable output objects.
   *
   * @param rd Readable input object
   * @param ap Appendable output object
   * @throws IllegalArgumentException if either object is null
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException();
    }
    in = new Scanner(rd);
    out = ap;
  }

  /**
   * Plays the Game according to given model using the Appendable and Readable from construction.
   *
   * @param model The model object to be used in playing this game of marble solitaire
   * @throws IllegalArgumentException if model is null
   * @throws IllegalStateException    if Readable unable to provide input or Appendable unable to
   *                                  transmit output
   */
  @Override
  public void playGame(MarbleSolitaireModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.situation("", model);
    ArrayList<Integer> moves = new ArrayList<>(4);
    while (in.hasNext()) {
      String next = in.next();
      if (next.equalsIgnoreCase("q")) {
        this.situation("Game quit!\nState of game when quit:\n", model);
        return;
      } else if (model.isGameOver()) {
        this.situation("Game over!\n", model);
        return;
      } else {
        int i = 0;
        boolean isInt = true;
        try {
          i = Integer.parseInt(next);
        } catch (NumberFormatException e) {
          isInt = false;
        }
        if (isInt && i >= 1) {
          moves.add(i - 1);
        } else {
          this.situation("Input number " + (moves.size() + 1)
                  + " is invalid, try again.\n", model);
        }
        if (moves.size() == 4) {
          try {
            model.move(moves.remove(0), moves.remove(0),
                    moves.remove(0), moves.remove(0));
            if (model.isGameOver()) {
              this.situation("Game over!\n", model);
              return;
            } else {
              this.situation("", model);
            }
          } catch (IllegalArgumentException e) {
            this.situation("Invalid move. Play again.\n", model);
          }
        }
      }
    }
    throw new IllegalStateException();
  }

  private void situation(String sit, MarbleSolitaireModel model) {
    try {
      out.append(sit);
      out.append(model.getGameState());
      out.append("\nScore: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
