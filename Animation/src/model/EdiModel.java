package model;

import keyframe.KeyFrame;
import shapes.Shape;

public interface EdiModel extends AnimModel {
  Shape getEdiShape(String id);

  void removeShape(String id);

  /**
   * adds the given shape to the HashMap, with the given id as the key.
   *
   * @param shape the shape to be added
   * @param id    the key for the shape
   */
  void addShape(Shape shape, String id);

  /**
   * adds a keyFrame to the shape of the given id.
   *
   * @param id   name of the shape for the keyFrame to be added
   * @param time the time the keyFrame should be added at
   * @param kf   the keyFrame to be added
   * @throws IllegalArgumentException if the id of the shape is null.
   */
  void addKeyFrame(String id, int time, KeyFrame kf);
}
