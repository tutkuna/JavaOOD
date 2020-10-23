package cs3500.animator.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import keyframe.KeyFrame;
import model.AnimModel;
import shapes.ReadShape;

/**
 * Package private helper class for visual view. This class represents the panel for a visual view
 * of the animation, as well as the operations that help to facilitate this.
 */
class AnimPanel extends JPanel {
  private AnimModel model;
  private int tick;

  /**
   * Constructs an animation panel with/for the given model.
   *
   * @param model the given model
   */
  AnimPanel(AnimModel model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (ReadShape s : model.readAll()) {
      draw(s, g);
    }
  }

  /**
   * draws the given shape using g.
   *
   * @param s the given shape
   * @param g the graphics from paintComponent
   * @throws IllegalArgumentException if a shape that isn't implemented is tried to be drawn
   */
  private void draw(ReadShape s, Graphics g) throws IllegalArgumentException {
    KeyFrame kyf;
    try {
      kyf = s.tween(tick);
    } catch (IllegalArgumentException e) {
      return;
    }
    g.setColor(kyf.getColor());
    switch (s.getType()) {
      case "ellipse":
        g.fillOval(kyf.getX(), kyf.getY(), kyf.getWidth(), kyf.getHeight());
        break;
      case "rectangle":
        g.fillRect(kyf.getX(), kyf.getY(), kyf.getWidth(), kyf.getHeight());
        break;
      default:
        throw new IllegalArgumentException("Shape not implemented in view");
    }
  }

  /**
   * Advances one tick and repaints.
   *
   * @throws IllegalArgumentException if repaint throws IllegalArgumentException
   */
  void tick() throws IllegalArgumentException {
    tick++;
    repaint();
  }

  /**
   * Checks if the animation is done.
   *
   * @return true if the animation is complete
   */
  boolean done() {
    int last = 0;
    for (ReadShape s : model.readAll()) {
      ArrayList<Integer> temp = s.getKeyTimes();
      last = Math.max(last, temp.get(temp.size() - 1));
    }
    return tick == last;
  }
}
