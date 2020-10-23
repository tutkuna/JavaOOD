package shapes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import keyframe.KeyFrame;
import keyframe.KeyFrameImpl;

/**
 * <title>lovingly called abbie shappie.</title>
 * This class represents the abstract constructor, functions and storage of KeyFrames for shapes.
 * INVARIANT: can never exist at a negative time, which means there is no keyFrame with negative key
 * time.
 */
public abstract class AbstractShape implements Shape {

  private HashMap<Integer, KeyFrame> frames;

  /**
   * Constructs a new Shape with a single keyFrame at given time, with given values.
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

  public AbstractShape(int time, int xPos, int yPos, int width, int height, Color col) {
    this(time, new KeyFrameImpl(xPos, yPos, width, height, col));
  }

  /**
   * Constructs a new Shape with the given KeyFrame assigned at the given time. The KeyFrame can be
   * assigned directly since it is an object without any mutator methods and only final variables so
   * it can't be edited elsewhere.
   *
   * @param time     time shape is created (initially)
   * @param keyFrame the keyframe that represents the shape at time of creation
   * @throws IllegalArgumentException if the given time is negative or the given keyFrame is null.
   */
  public AbstractShape(int time, KeyFrame keyFrame) {
    if (time < 0) {
      throw new IllegalArgumentException("Shape can't be created at negative time");
    }
    if (keyFrame == null) {
      throw new IllegalArgumentException("Can't construct with null keyFrame");
    }
    frames = new HashMap<>();
    frames.put(time, keyFrame);
  }

  /**
   * Creates this shape without any keyFrames and sets it visible at creation.
   */
  public AbstractShape() {
    frames = new HashMap<>();
  }

  @Override
  public abstract String getType();

  @Override
  public void addFrame(int tick, KeyFrame frame) throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("Can't add a frame at negative time");
    }
    if (frame == null) {
      throw new IllegalArgumentException("Can't add null KeyFrame");
    }
    frames.put(tick, frame);
  }

  @Override
  public KeyFrame getFrame(int t) throws IllegalArgumentException {
    KeyFrame f = frames.get(t);
    if (f == null) {
      throw new IllegalArgumentException("No KeyFrame at the given time");
    }
    return f;
  }

  @Override
  public ArrayList<Integer> getKeyTimes() {
    ArrayList<Integer> keys = new ArrayList<>(frames.keySet());
    Collections.sort(keys);
    return keys;
  }

  @Override
  public void removeFrame(int i) throws IllegalArgumentException {
    if (frames.get(i) == null) {
      throw new IllegalArgumentException("No KeyFrame at the given time");
    }
    frames.remove(i);
  }

  @Override
  public KeyFrame tween(int t) throws IllegalArgumentException {
    int prev = 0;
    for (int tick : this.getKeyTimes()) {
      if (t >= prev && t <= tick) {
        return new KeyFrameImpl(
                this.tweenHelp(this.getFrame(prev).getX(), this.getFrame(tick).getX(),
                        prev, tick, t),
                this.tweenHelp(this.getFrame(prev).getY(), this.getFrame(tick).getY(),
                        prev, tick, t),
                this.tweenHelp(this.getFrame(prev).getWidth(), this.getFrame(tick).getWidth(),
                        prev, tick, t),
                this.tweenHelp(this.getFrame(prev).getHeight(), this.getFrame(tick).getHeight(),
                        prev, tick, t),
                new Color(this.tweenHelp(this.getFrame(prev).getColor().getRed(),
                        this.getFrame(tick).getColor().getRed(), prev, tick, t),
                        this.tweenHelp(this.getFrame(prev).getColor().getGreen(),
                                this.getFrame(tick).getColor().getGreen(),
                                prev, tick, t),
                        this.tweenHelp(this.getFrame(prev).getColor().getBlue(),
                                this.getFrame(tick).getColor().getBlue(),
                                prev, tick, t)));
      }
      prev = tick;
    }
    throw new IllegalArgumentException("no presence");
  }

  /**
   * Helps do the math for the tween method.
   *
   * @param a  value at ta
   * @param b  value at tb
   * @param ta start of interval
   * @param tb end of interval
   * @param t  the time we are at, which will be in the interval through the calling
   * @return the tweened result for the tween method to use.
   */
  private int tweenHelp(double a, double b, double ta, double tb, double t) {
    return (int) Math.round(a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));
  }
}
