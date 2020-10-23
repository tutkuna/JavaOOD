package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import java.io.InputStreamReader;

/**
 * Represents the main of MarbleSolitaire game as runnable with command line arguments.
 */
public final class MarbleSolitaire {
  /**
   * The main method.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = null;
    if (args.length == 1) {
      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl();
          break;
        case "european":
          model = new EuropeanSolitaireModelImpl();
          break;
        case "triangle":
          model = new TriangleSolitaireModelImpl();
          break;
        default: //invalid command line not handled as in assignment description
      }
    }
    if (args.length == 3) {
      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl(Integer.parseInt(args[2]));
          break;
        case "european":
          model = new EuropeanSolitaireModelImpl(Integer.parseInt(args[2]));
          break;
        case "triangle":
          model = new TriangleSolitaireModelImpl(Integer.parseInt(args[2]));
          break;
        default: //invalid command line not handled as in assignment description
      }
    }
    if (args.length == 4) {
      switch (args[0]) {
        case "english":
          model = new MarbleSolitaireModelImpl(Integer.parseInt(args[2]),
                  Integer.parseInt(args[3]));
          break;
        case "european":
          model = new EuropeanSolitaireModelImpl(Integer.parseInt(args[2]),
                  Integer.parseInt(args[3]));
          break;
        case "triangle":
          model = new TriangleSolitaireModelImpl(Integer.parseInt(args[2]),
                  Integer.parseInt(args[3]));
          break;
        default: //invalid command line not handled as in assignment description
      }
    }
    if (args.length == 6) {
      switch (args[0]) {
        case "english":
          if (args[1].equalsIgnoreCase("-size")) {
            model = new MarbleSolitaireModelImpl(Integer.parseInt(args[2]),
                    Integer.parseInt(args[4]), Integer.parseInt(args[5]));
          } else {
            model = new MarbleSolitaireModelImpl(Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]), Integer.parseInt(args[5]));
          }
          break;
        case "european":
          if (args[1].equalsIgnoreCase("-size")) {
            model = new EuropeanSolitaireModelImpl(Integer.parseInt(args[2]),
                    Integer.parseInt(args[4]), Integer.parseInt(args[5]));
          } else {
            model = new EuropeanSolitaireModelImpl(Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]), Integer.parseInt(args[5]));
          }
          break;
        case "triangle":
          if (args[1].equalsIgnoreCase("-size")) {
            model = new TriangleSolitaireModelImpl(Integer.parseInt(args[2]),
                    Integer.parseInt(args[4]), Integer.parseInt(args[5]));
          } else {
            model = new TriangleSolitaireModelImpl(Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]), Integer.parseInt(args[5]));
          }
          break;
        default: //invalid command line not handled as in assignment description
      }
    }
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(rd, System.out);
    controller.playGame(model);
  }
}