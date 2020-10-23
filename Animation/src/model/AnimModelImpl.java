package model;

import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cs3500.animator.util.AnimationBuilder;
import keyframe.KeyFrame;
import keyframe.KeyFrameImpl;
import shapes.Ellipse;
import shapes.ReadShape;
import shapes.Rectangle;
import shapes.Shape;

/**
 * Represents the model for the EasyAnimator with a HashMap storing shapes with their string ids as
 * keys. Has methods for all aspects of CRUD (create, read, update, and delete).
 */
public class AnimModelImpl implements EdiModel {

  private HashMap<String, Shape> map;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Creates a model with an empty HashMap.
   */
  private AnimModelImpl() {
    map = new HashMap<>();
  }

  @Override
  public void addShape(Shape shape, String id) {
    map.put(id, shape);
  }

  @Override
  public void addKeyFrame(String id, int time, KeyFrame frame) throws IllegalArgumentException {
    if (map.get(id) == null) {
      throw new IllegalArgumentException("No such shape");
    }
    if (time < 0) {
      throw new IllegalArgumentException("Can't add keyFrame at negative time");
    }
    map.get(id).addFrame(time, frame);
  }

  @Override
  public ArrayList<ReadShape> readAll() {
    return new ArrayList<>(map.values());
  }

  /**
   * adds a motion to an animation.
   *
   * @param id  the id of the shape to add the motion to
   * @param t1  the start time
   * @param kf1 the start keyFrame
   * @param t2  the end time
   * @param kf2 the end keyFrame
   * @throws IllegalArgumentException if the time is negative
   */
  private void addMotion(String id, int t1, KeyFrame kf1, int t2, KeyFrame kf2)
          throws IllegalArgumentException {
    if (t1 < 0 || t2 < 0) {
      throw new IllegalArgumentException("Can't add keyFrame at negative time");
    }
    this.addKeyFrame(id, t1, kf1);
    this.addKeyFrame(id, t2, kf2);
  }

  /**
   * setting the bounds of the animation for the view.
   *
   * @param x      the x position
   * @param y      the y position
   * @param width  the width
   * @param height the height
   */
  private void setBounds(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public ArrayList<String> getIDs() {
    ArrayList<String> ids = new ArrayList<>(map.keySet());
    Collections.sort(ids);
    return ids;
  }

  @Override
  public ReadShape getShape(String id) {
    return map.get(id);
  }

  @Override
  public Shape getEdiShape(String id) {
    return map.get(id);
  }

  @Override
  public int getBounds(String s) throws IllegalArgumentException {
    switch (s) {
      case "x":
        return this.x;
      case "y":
        return this.y;
      case "w":
        return this.width;
      case "h":
        return this.height;
      default:
        throw new IllegalArgumentException("no such bound");
    }
  }

  @Override
  public Dimension getMax() {
    int x = 0;
    int y = 0;
    for (ReadShape s : map.values()) {
      for (int t : s.getKeyTimes()) {
        KeyFrame kf = s.getFrame(t);
        x = Math.max(kf.getX() + kf.getWidth(), x);
        y = Math.max(kf.getY() + kf.getHeight(), y);
      }
    }
    return new Dimension(x, y);
  }

  @Override
  public void removeShape(String id) {
    map.remove(id);
  }

  /**
   * Creates and returns a new instance of the static builder for this class.
   *
   * @return a new instance of the builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * This class represents the builder for AnimModelImpl and the functions that help facilitate it.
   */
  public static final class Builder implements AnimationBuilder<EdiModel> {

    private AnimModelImpl model;

    /**
     * Constructs a new instance of an AnimModelImpl to be built upon.
     */
    Builder() {
      this.model = new AnimModelImpl();
    }

    @Override
    public EdiModel build() {
      return model;
    }

    @Override
    public AnimationBuilder<EdiModel> setBounds(int x, int y, int width, int height) {
      model.setBounds(x, y, width, height);
      return this;
    }

    @Override
    public AnimationBuilder<EdiModel> declareShape(String name, String type)
            throws IllegalArgumentException {
      switch (type) {
        case "rectangle":
          model.addShape(new Rectangle(), name);
          break;
        case "ellipse":
          model.addShape(new Ellipse(), name);
          break;
        default:
          throw new IllegalArgumentException("No such shape type");
      }
      return this;
    }

    @Override
    public AnimationBuilder<EdiModel> addMotion(String name,
                                                int t1, int x1, int y1, int w1, int h1,
                                                int r1, int g1, int b1,
                                                int t2, int x2, int y2, int w2, int h2,
                                                int r2, int g2, int b2) {
      KeyFrame kf1 = new KeyFrameImpl(x1, y1, w1, h1, new Color(r1, g1, b1));
      KeyFrame kf2 = new KeyFrameImpl(x2, y2, w2, h2, new Color(r2, g2, b2));
      model.addMotion(name, t1, kf1, t2, kf2);
      return this;
    }

    @Override
    public AnimationBuilder<EdiModel> addKeyframe(String name, int t, int x, int y, int w, int h,
                                                  int r, int g, int b) {
      model.addKeyFrame(name, t, new KeyFrameImpl(x, y, w, h, new Color(r, g, b)));
      return this;
    }
  }
}

