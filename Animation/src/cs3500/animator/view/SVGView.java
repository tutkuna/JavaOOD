package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import keyframe.KeyFrame;
import model.AnimModel;
import shapes.ReadShape;

/**
 * This class represents a SVG view for the animation, and the operations that help to facilitate
 * it.
 */
public class SVGView implements AniView {
  private AnimModel model;
  private Appendable app;
  private int milt;

  /**
   * Constructs an SVG view with the given parameters. INVARIANT: tps has to be a positive int
   * and greater than zero.
   * @param model the model to be animated
   * @param print the appendable for the animation to be printed to
   * @param tps   the ticks per second, which will be converted to a millisecond delay (milt)
   * @throws IllegalArgumentException if the model or appendable is null or if tps is negative
   */
  public SVGView(AnimModel model, Appendable print, int tps) {
    if (model == null || print == null || tps <= 0) {
      throw new IllegalArgumentException("null passed in or tps set <= 0");
    }
    this.model = model;
    this.app = print;
    this.milt = 1000 / tps;
  }

  /**
   * saves the animation into the given appendable in SVG format.
   * @throws IllegalArgumentException if the given appendable can't be appended to
   */
  @Override
  public void run() throws IllegalArgumentException {
    try {
      app.append("<svg viewBox=\"" + model.getBounds("x") + " " + model.getBounds("y") + " "
              + model.getBounds("w") + " " + model.getBounds("h")
              + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" >\n");
      for (String s : model.getIDs()) {
        ReadShape shape = model.getShape(s);
        ArrayList<Integer> keys = shape.getKeyTimes();
        KeyFrame init = shape.getFrame(keys.get(0));
        switch (shape.getType()) {
          case "rectangle":
            app.append("<rect id=\"" + s + "\" x=\"" + init.getX() + "\" y=\"" + init.getY()
                    + "\" width=\"" + init.getWidth() + "\" height=\"" + init.getHeight()
                    + "\" fill=\"rgb(" + init.getColor().getRed() + ", "
                    + init.getColor().getGreen() + ", " + init.getColor().getBlue()
                    + ")\" visibility=\"hidden\" >\n");
            app.append(printAnims(shape));
            app.append(printAnimHelp("" + keys.get(keys.size() - 1) * milt, "" + 1,
                    "visibility", "visible", "hidden"));
            app.append("</rect>\n");
            break;
          case "ellipse":
            app.append("<ellipse id=\"" + s
                    + "\" cx=\"" + (init.getX() + init.getWidth() / 2)
                    + "\" cy=\"" + (init.getY() + init.getHeight() / 2)
                    + "\" rx=\"" + init.getWidth() / 2 + "\" ry=\"" + init.getHeight() / 2
                    + "\" fill=\"rgb(" + init.getColor().getRed() + ", "
                    + init.getColor().getGreen() + ", " + init.getColor().getBlue()
                    + ")\" visibility=\"hidden\" >\n");
            app.append(printAnims(shape));
            app.append(printAnimHelp("" + keys.get(keys.size() - 1) * milt, "" + 1,
                    "visibility", "visible", "hidden"));
            app.append("</ellipse>\n");
            break;
          default:
            throw new IllegalArgumentException("Shape not in SVGView");
        }
      }
      app.append("</svg>");
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't append to given appendable");
    }
  }

  /**
   * Concatenates all the animations of the given shape.
   *
   * @param shape the given shape
   * @return the string of all the concatenated animations.
   */
  private String printAnims(ReadShape shape) {
    StringBuffer output = new StringBuffer();
    ArrayList<Integer> keys = shape.getKeyTimes();
    Iterator<Integer> it = keys.iterator();
    if (!it.hasNext()) {
      return "";
    }
    int first = it.next();
    output.append(printAnimHelp("" + first * milt, "" + 1, "visibility",
            "hidden", "visible"));
    while (it.hasNext()) {
      int i = it.next();
      if (shape.getType().equalsIgnoreCase("rectangle")) {
        output.append(printRect(shape.getFrame(first), shape.getFrame(i), first, i));
      } else if (shape.getType().equalsIgnoreCase("ellipse")) {
        output.append(printEllie(shape.getFrame(first), shape.getFrame(i), first, i));
      }
      first = i;
    }
    return output.toString();
  }

  /**
   * Prints a single animation of a rectangle.
   *
   * @param kf1 the starting keyFrame
   * @param kf2 the end keyFrame
   * @param t1  the start time
   * @param t2  the end time
   * @return String of a single animation of a rectangle.
   */
  private String printRect(KeyFrame kf1, KeyFrame kf2, int t1, int t2) {
    String out = "";
    if (kf1.getX() != kf2.getX()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "x", ""
              + kf1.getX(), "" + kf2.getX());
    }
    if (kf1.getY() != kf2.getY()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "y", ""
              + kf1.getY(), "" + kf2.getY());
    }
    if (kf1.getWidth() != kf2.getWidth()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "width", ""
              + kf1.getWidth(), "" + kf2.getWidth());
    }
    if (kf1.getHeight() != kf2.getHeight()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "height", ""
              + kf1.getHeight(), "" + kf2.getHeight());
    }
    if (kf1.getColor().getRGB() != kf2.getColor().getRGB()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "fill",
              printRGB(kf1), printRGB(kf2));
    }
    return out;
  }

  /**
   * Prints a single animation of an ellipse.
   *
   * @param kf1 the starting keyFrame
   * @param kf2 the end keyFrame
   * @param t1  the start time
   * @param t2  the end time
   * @return String of a single animation of an ellipse.
   */
  private String printEllie(KeyFrame kf1, KeyFrame kf2, int t1, int t2) {
    String out = "";
    int rx1 = kf1.getWidth() / 2;
    int ry1 = kf1.getHeight() / 2;
    int rx2 = kf2.getWidth() / 2;
    int ry2 = kf2.getHeight() / 2;
    if (kf1.getX() != kf2.getX()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "cx", ""
              + (kf1.getX() + rx1), "" + (kf2.getX() + rx2));
    }
    if (kf1.getY() != kf2.getY()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "cy", ""
              + (kf1.getY() + ry1), "" + (kf2.getY() + ry2));
    }
    if (kf1.getWidth() != kf2.getWidth()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "rx", ""
              + rx1, "" + rx2);
    }
    if (kf1.getHeight() != kf2.getHeight()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "ry", ""
              + ry1, "" + ry2);
    }
    if (kf1.getColor().getRGB() != kf2.getColor().getRGB()) {
      out += printAnimHelp("" + milt * t1, "" + (t2 - t1) * milt, "fill",
              printRGB(kf1), printRGB(kf2));
    }
    return out;
  }

  /**
   * Helper method for printAnim. Concatenates the descriptive words to match their corresponding
   * animations in order to produce the string of an animation in SVG format.
   *
   * @param t1   the start time
   * @param dur  the duration of the animation
   * @param attr the attribute to be animated
   * @param from the beginning value of the attribute
   * @param to   the end value of the attribute
   * @return the string of an animation in SVG format
   */
  private String printAnimHelp(String t1, String dur, String attr, String from, String to) {
    return "<animate attributeType=\"xml\" begin=\"" + t1 + "ms\" dur=\""
            + dur + "ms\" attributeName=\"" + attr + "\" from=\"" + from + "\" to=\""
            + to + "\" fill=\"freeze\" />\n";
  }

  /**
   * Helper method for printAnim. Formats the RGB values into SVG format.
   *
   * @param kf the keyframe to retrieve the colors
   * @return String of the colors in SVG format
   */
  private String printRGB(KeyFrame kf) {
    return "rgb(" + kf.getColor().getRed() + ", " + kf.getColor().getGreen() + ", "
            + kf.getColor().getBlue() + ")";
  }

}
