package keyframe;

import java.awt.Color;

/**
 * This interface represents the operations offered by the KeyFrame interface. One object of the
 * implementation of this interface represents the state of a shape at a frame.
 */
public interface KeyFrame {

  /**
   * Gets the x position at this KeyFrame.
   *
   * @return the int x position at this KeyFrame
   */
  int getX();

  /**
   * Gets the y position at this KeyFrame.
   *
   * @return the int y position at this KeyFrame
   */
  int getY();

  /**
   * Gets the width value at this KeyFrame.
   *
   * @return the int width at this KeyFrame
   */
  int getWidth();

  /**
   * Gets the height value at this KeyFrame.
   *
   * @return the int height at this KeyFrame
   */
  int getHeight();

  /**
   * Gets the color value at this KeyFrame.
   *
   * @return the Color at this KeyFrame
   */
  Color getColor();
}



