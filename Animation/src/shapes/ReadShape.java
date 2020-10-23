package shapes;

import java.util.ArrayList;

import keyframe.KeyFrame;

/**
 * Read only interface for the Shape and its getters.
 */
public interface ReadShape {
  /**
   * Gets the type of the Shape as a String.
   *
   * @return the type of Shape as a String.
   */
  String getType();

  /**
   * Gets the keyFrame of this Shape at the given time.
   *
   * @param i the time you want to get the Frame at.
   * @return a KeyFrame that contains all properties of the shape at that time
   * @throws IllegalArgumentException if there is no KeyFrame at the given time
   */
  KeyFrame getFrame(int i) throws IllegalArgumentException;

  /**
   * Gets an ordered list of the times where this shape has a keyFrame.
   *
   * @return a list (ordered) of times where this Shape has a keyFrame.
   */
  ArrayList<Integer> getKeyTimes();

  /**
   * Gets the frame at the given time even if it isn't a keyFrame.
   *
   * @param t the time you want to get the frame at
   * @return a KeyFrame that that contains all properties of the shape at that time
   */
  KeyFrame tween(int t) throws IllegalArgumentException;
}
