package shapes;

import keyframe.KeyFrame;

/**
 * This interface represents the operations offered by the Shape interface. One object of the
 * implementation of this interface represents a shape that stores its values at key times.
 */
public interface Shape extends ReadShape {
  /**
   * Adds new frame at the given tick or replaces the frame at the given tick if one already exists
   * at that tick.
   *
   * @param tick represents the tick we're at in time
   * @param kf   the KeyFrame to be assigned to that tick for this shape
   * @throws IllegalArgumentException if tick is negative or the kf is null.
   */
  void addFrame(int tick, KeyFrame kf) throws IllegalArgumentException;

  /**
   * Removes the frame at the given time.
   *
   * @param i tick time where the frame should be removed
   * @throws IllegalArgumentException if there is no keyFrame at the given time
   */
  void removeFrame(int i) throws IllegalArgumentException;
}