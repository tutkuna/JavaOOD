package cs3500.animator.view;

/**
 * This interface represents the operations offered by the AniView interface. One object of an
 * implementation of this interface would represent a type of viewing of the animation (there are
 * multiple types).
 */
public interface AniView {
  /**
   * Runs the view, which does different things for each implementation.
   *
   * @throws IllegalArgumentException if appendable unable to be appended to or an unimplemented
   *                                  shape is saved in the model to be run on
   */
  void run() throws IllegalArgumentException;
}
