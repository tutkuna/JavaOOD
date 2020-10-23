package shapes;

import java.awt.Color;

import keyframe.KeyFrame;

/**
 * This class represents the ellipse shape that inherits from AbstractShape.
 */
public class Ellipse extends AbstractShape {
  /**
   * Constructs a new Ellipse with a single keyFrame at given time, with given values.
   *
   * @param time   time the Shape was first created
   * @param xPos   x position of the Shape at the time of creation
   * @param yPos   y position of the Shape at the time of creation
   * @param width  width of the Shape at the time of creation
   * @param height height of the Shape at the time of creation
   * @param col    color of the Shape at the time of creation
   * @throws IllegalArgumentException if the color is null, if the given time is negative or the
   *                                  given keyFrame is null.
   */
  public Ellipse(int time, int xPos, int yPos, int width, int height, Color col) {
    super(time, xPos, yPos, width, height, col);
  }

  /**
   * Constructs a new Ellipse with the given KeyFrame assigned at the given time. The KeyFrame can
   * be assigned directly since it is an object without any mutator methods and only final variables
   * so it can't be edited elsewhere.
   *
   * @param time time shape is created (initially)
   * @param kyf  the keyframe that represents the shape at time of creation
   * @throws IllegalArgumentException if the given time is negative or the given keyFrame is null.
   */
  public Ellipse(int time, KeyFrame kyf) {
    super(time, kyf);
  }

  /**
   * Creates this ellipse without any keyFrames and sets it visible at creation.
   */
  public Ellipse() {
    super();
  }

  @Override
  public String getType() {
    return "ellipse";
  }
}