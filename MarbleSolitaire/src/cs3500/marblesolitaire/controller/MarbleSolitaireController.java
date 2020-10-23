package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This interface represents the operations offered by the marble solitaire controller. One object
 * of MarbleSolitaireController can play the game given a model.
 */
public interface MarbleSolitaireController {

  /**
   * Plays the marble solitaire game using given model.
   *
   * @param model The model object to be used in playing this game of marble solitaire
   * @throws IllegalArgumentException if model is null
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException;
}
