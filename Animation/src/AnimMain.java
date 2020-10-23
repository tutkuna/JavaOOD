import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JDialog;

import cs3500.animator.controller.EdiCont;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AniView;
import cs3500.animator.view.EdiView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;
import model.AnimModel;
import model.AnimModelImpl;
import model.EdiModel;

/**
 * We run the program via this class, used for user input testing.
 */
public class AnimMain {
  /**
   * The initial piece of code to run/entry point for the program-- Used for user input testing.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Iterator<String> it = Arrays.asList(args).iterator();
    Readable red = null;
    PrintStream print = System.out;
    String out = "";
    String type = "";
    int speed = 1;
    while (it.hasNext()) {
      String mod = it.next();
      if (!it.hasNext()) {
        throw new IllegalArgumentException("Ran out of command-line prematurely");
      }
      if (mod.equalsIgnoreCase("-in")) {
        red = useFile(it.next());
      } else if (mod.equalsIgnoreCase("-out")) {
        out = it.next();
      } else if (mod.equalsIgnoreCase("-view")) {
        type = it.next();
      } else if (mod.equalsIgnoreCase("-speed")) {
        speed = Integer.parseInt(it.next());
      } else {
        JOptionPane pane = new JOptionPane("INVALID COMMAND LINE ARG: \"" + mod + "\"",
                JOptionPane.ERROR_MESSAGE);
        JDialog dialog = pane.createDialog("ERROR");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        System.exit(0);
      }
    }
    if (!out.isEmpty()) {
      String end = "";
      if (out.length() >= 4) {
        end = out.substring(out.length() - 4);
      }
      if (type.equalsIgnoreCase("text") &&
              !end.equalsIgnoreCase(".txt")) {
        out += ".txt";
      } else if (type.equalsIgnoreCase("SVG") &&
              !end.equalsIgnoreCase(".svg")) {
        out += ".svg";
      }
      File f = new File(out);
      try {
        f.createNewFile();
        print = new PrintStream(f);
      } catch (IOException e) {
        throw new IllegalArgumentException("File can't be found or created");
      }
    }


    if (red == null || type.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("Required command-line not found");
    }
    EdiModel model = AnimationReader.parseFile(red, AnimModelImpl.builder());
    AniView view = null;
    switch (type) {
      case "visual":
        view = new VisualView(model, speed);
        break;
      case "text":
        view = new TextView(model, print);
        break;
      case "SVG":
        view = new SVGView(model, print, speed);
        break;
      case "edit":
        view = new EdiView(speed);
        break;
      default:
        throw new IllegalArgumentException("Not an available view type");
    }
    try {
      view.run();
    } catch (UnsupportedOperationException e) {
      new EdiCont((EdiView) view, model, speed).run();
    }
  }

  /**
   * Creates a readable for the file of the given name.
   *
   * @param fileName the file name
   * @return an readable for the file
   * @throws IllegalArgumentException if the input file is not found
   */
  private static Readable useFile(String fileName) throws IllegalArgumentException {
    try {
      return new BufferedReader(new FileReader(fileName));
    } catch (IOException e) {
      throw new IllegalArgumentException("Input file not found");
    }
  }
}
