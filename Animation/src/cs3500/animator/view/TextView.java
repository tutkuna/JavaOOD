package cs3500.animator.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import keyframe.KeyFrame;
import model.AnimModel;
import shapes.ReadShape;

/**
 * This class represents a Text view for the animation, and the operations that help to facilitate
 * it.
 */
public class TextView implements AniView {
  private AnimModel model;
  private Appendable appendable;

  /**
   * Constructs a text view with the given parameters.
   *
   * @param model      the model to be animated
   * @param appendable the appendable for the animation to be printed to
   * @throws IllegalArgumentException if the model or appendable passed in is null
   */
  public TextView(AnimModel model, Appendable appendable) {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("null passed in");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * saves the animation into the given appendable in text format.
   * @throws IllegalArgumentException if the given appendable cannot be appended to.
   */
  @Override
  public void run() throws IllegalArgumentException {
    StringBuffer out = new StringBuffer();
    for (String s : model.getIDs()) {
      out.append("shape\t" + s + "\t" + model.getShape(s).getType() + "\n");
      out.append(this.printFrames(model.getShape(s), s) + "\n");
    }
    try {
      appendable.append(out);
    } catch (IOException e) {
      throw new IllegalArgumentException("Not appendable");
    }
  }

  /**
   * Formats all the frames of the shape to a String.
   *
   * @param shape the shape to retrieve the frames from
   * @param name  the name of the shape
   * @return a formatted string of all the frames of the shape
   */
  private String printFrames(ReadShape shape, String name) {
    ArrayList<Integer> keys = shape.getKeyTimes();
    StringBuffer output = new StringBuffer();
    Iterator<Integer> it = keys.iterator();
    if (!it.hasNext()) {
      return "";
    }
    int first = it.next();
    output.append("motion\t" + name + "\t" + first + "\t"
            + printFrame(shape.getFrame(first)) + "\t\t");
    while (it.hasNext()) {
      int i = it.next();
      output.append(i + "\t" + printFrame(shape.getFrame(i)) + "\n");
      if (it.hasNext()) {
        output.append("motion\t" + name + "\t" + i + "\t" + printFrame(shape.getFrame(i))
                + "\t\t");
      }
    }
    return output.toString();
  }

  /**
   * Helper method for printFrames. Formats a single frame of a shape into a string.
   *
   * @param frame the frame to be formatted
   * @return a string of the formatted frame of a shape
   */
  private String printFrame(KeyFrame frame) {
    return "" + frame.getX() + "\t" + frame.getY() + "\t" + frame.getWidth() + "\t"
            + frame.getHeight()
            + "\t" + frame.getColor().getRed() + "\t" + frame.getColor().getGreen() + "\t"
            + frame.getColor().getBlue();
  }
}
