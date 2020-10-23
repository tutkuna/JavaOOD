package keyframe;

import java.awt.Color;

/**
 * Represents the state of a shape by its dimensions, position, and color. INVARIANT: color can
 * never be null.
 */
public class KeyFrameImpl implements KeyFrame {

  private final int width;
  private final int height;
  private final int posX;
  private final int posY;
  private final Color col;

  /**
   * Creates a new KeyFrame with given values as final for the width, height, posX, posY, and
   * color.
   *
   * @param xPos   x position at this frame
   * @param yPos   y position at this frame
   * @param width  width at this frame
   * @param height height at this frame
   * @param col    color at this frame
   * @throws IllegalArgumentException if the constructor is given a null value for color.
   */
  public KeyFrameImpl(int xPos, int yPos, int width, int height, Color col) {
    if (col == null) {
      throw new IllegalArgumentException("a KeyFrame can't have null color");
    }
    if (width < 0) {
      this.width = -width;
      posX = xPos - this.width;
    } else {
      this.width = width;
      this.posX = xPos;
    }
    if (height < 0) {
      this.height = -height;
      this.posY = yPos - this.height;
    } else {
      this.height = height;
      this.posY = yPos;
    }
    this.col = col;
  }

  @Override
  public int getX() {
    return posX;
  }

  @Override
  public int getY() {
    return posY;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Color getColor() {
    return col;
  }
}