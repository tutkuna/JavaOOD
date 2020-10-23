package cs3500.animator.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import keyframe.KeyFrame;
import shapes.ReadShape;

public class EdiPanel extends JPanel {
  ArrayList<ReadShape> shapes;
  int tick;

  /**
   * Constructs an animation panel with/for the given model.
   *
   * @param shapes the shapes to be drawn
   */
  EdiPanel(ArrayList<ReadShape> shapes) {
    this.shapes = shapes;
    this.tick = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (ReadShape s : shapes) {
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
      case "rectangle":
        g.fillRect(kyf.getX(), kyf.getY(), kyf.getWidth(), kyf.getHeight());
        break;
      case "ellipse":
        g.fillOval(kyf.getX(), kyf.getY(), kyf.getWidth(), kyf.getHeight());
        break;
      default:
        throw new IllegalArgumentException("Shape not implemented in view");
    }
  }

  void draw(ArrayList<ReadShape> shapes, int tick) {
    this.shapes = shapes;
    this.tick = tick;
    repaint();
  }
}

