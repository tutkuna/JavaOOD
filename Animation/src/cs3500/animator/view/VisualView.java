package cs3500.animator.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.AnimModel;

/**
 * This class represents a visual view for the animation, and the operations that help to facilitate
 * it.
 */
  public class VisualView extends JFrame implements AniView {

    private AnimPanel panel;
    private int tps;

  /**
   * Constructs a visual view with the given parameters. INVARIANT: tps has to be a positive int and
   * greater than zero.
   *
   * @param model the model to be animated
   * @param tps   the ticks per second
   * @throws IllegalArgumentException if the given model is null or if the tps is negative
   **/
  public VisualView(AnimModel model, int tps) {
    if (model == null || tps <= 0) {
      throw new IllegalArgumentException("null passed in or tps <= 0");
    }
    panel = new AnimPanel(model);
    panel.setPreferredSize(model.getMax());
    JScrollPane pane = new JScrollPane(panel);
    pane.setWheelScrollingEnabled(true);
    this.add(pane);
    this.setSize(model.getBounds("w"), model.getBounds("h"));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.tps = tps;
  }

  /**
   * Runs the visual animation with ticking speed based on tps.
   *
   * @throws IllegalArgumentException if a shape that isn't implemented in the view is tried to be
   *                                  drawn
   */
  @Override
  public void run() throws IllegalArgumentException {
    while (!panel.done()) {
      try {
        panel.tick();
        Thread.sleep(1000 / tps);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

