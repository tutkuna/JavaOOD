package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import cs3500.animator.controller.EdiFuncs;
import keyframe.KeyFrame;
import shapes.ReadShape;

public class EdiView extends JFrame implements IEdiView, ActionListener {
  private EdiPanel panel;
  private EdiFuncs listener;
  private JMenu shapes;
  private JMenu actions;
  private JMenu frames;
  private JMenuBar mb;
  private JMenu frameActs;

  /**
   * Constructs a visual view with the given parameters. INVARIANT: tps has to be a positive int and
   * greater than zero.
   *
   * @param tps the ticks per second
   * @throws IllegalArgumentException if the given model is null or if the tps is negative
   **/
  public EdiView(int tps) {
    if (tps <= 0) {
      throw new IllegalArgumentException("tps can't be <= 0");
    }
    listener = null;
  }

  public void setSituation() {
    panel = new EdiPanel(listener.readAll());
    panel.setPreferredSize(listener.getMax());
    JScrollPane pane = new JScrollPane(panel);
    pane.setWheelScrollingEnabled(true);
    this.setLayout(new BorderLayout());
    this.add(pane, BorderLayout.CENTER);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.add(this.createInstPane(), BorderLayout.SOUTH);
    this.setMinimumSize(new Dimension(800, 500));
    this.setSize(listener.getBound("w"), listener.getBound("h") + 40);

    mb = new JMenuBar();
    shapes = this.shapesMenu(listener.getIDs());
    mb.add(shapes);
    actions = new JMenu("Actions");
    JMenuItem remove = makeMenuItem("Remove Shape");
    JMenuItem addShape = makeMenuItem("Add/replace Shape");
    JMenuItem addFrame = makeMenuItem("Add Frame to Shape");
    actions.add(remove);
    actions.add(addShape);
    actions.add(addFrame);
    mb.add(actions);
    frames = framesMenu(listener.getIDs().get(0));
    mb.add(frames);
    frameActs = frameActs(listener.getShape(listener.getIDs().get(0)).getKeyTimes().get(0));
    mb.add(frameActs);
    this.add(mb, BorderLayout.NORTH);
    mb.add(new JMenuItem("Situtation: Not Started"));


    this.setVisible(true);
  }

  private JMenuItem curSitu() {
    return new JMenuItem("Situation: Speed-" + listener.speed() + " Paused-"
            + listener.paused() + " Looping-" + listener.looping());
  }

  private JMenu frameActs(int f) {
    JMenu m = new JMenu("Actions for Frame at: " + f);
    JMenuItem mod = makeMenuItem("Modify Frame");
    JMenuItem removeFrame = makeMenuItem("Remove Frame");
    m.add(mod);
    m.add(removeFrame);

    return m;
  }

  private JMenu shapesMenu(ArrayList<String> ids) {
    JMenu m = new JMenu("Select Shape");
    for (String id : ids) {
      m.add(makeMenuItem(id));
    }
    return m;
  }

  private JMenu framesMenu(String id) {
    JMenu m = new JMenu("Frames of: " + id);
    ArrayList<JMenu> menus = new ArrayList<>();
    ArrayList<JMenuItem> items = new ArrayList<>();
    for (int i : listener.getShape(id).getKeyTimes()) {
      items.add(makeMenuItem("" + i));
    }
    int count = 0;
    while (!items.isEmpty()) {
      int end = Math.min(items.size(), 30);
      menus.add(new JMenu("frames " + items.get(0).getText() + "-"
              + items.get(end - 1).getText()));
      for (int i = 0; i < end; i++) {
        menus.get(count).add(items.remove(0));
      }
      count++;
    }
    for (JMenu me : menus) {
      m.add(me);
    }
    return m;
  }

  private JMenuItem makeMenuItem(String id) {
    JMenuItem item = new JMenuItem(id);
    item.addActionListener(this);
    return item;
  }

  private JPanel createInstPane() {
    JPanel instPane = new JPanel(new GridLayout(4, 1));
    JLabel inst1 = new JLabel("Press \"SPACE\" to pause and resume.");
    inst1.setHorizontalAlignment(JLabel.CENTER);
    JLabel inst2 = new JLabel("Press \"ESCAPE\" to restart.");
    inst2.setHorizontalAlignment(JLabel.CENTER);
    JLabel inst3 = new JLabel("Press \"L\" to toggle looping. ");
    inst3.setHorizontalAlignment(JLabel.CENTER);
    instPane.add(inst1);
    JLabel inst4 = new JLabel("Press \"UP\" to speed up and \"DOWN\" to speed down. ");
    inst4.setHorizontalAlignment(JLabel.CENTER);
    JLabel inst5 = new JLabel("Press \"ENTER\" to start.");
    inst5.setHorizontalAlignment(JLabel.CENTER);
    instPane.add(inst5);
    instPane.add(inst2);
    instPane.add(inst3);
    instPane.add(inst4);
    return instPane;
  }

  public void draw(ArrayList<ReadShape> shapes, int tick) {
    panel.draw(shapes, tick);
  }

  public void setListener(EdiFuncs funs) {
    this.listener = funs;
  }

  public void update() {
    updateMenu(listener.getSelected(), listener.getSelFrame());
  }

  /**
   * Run is not supported as this view is only run through controller.
   */
  @Override
  public void run() throws IllegalArgumentException {
    throw new UnsupportedOperationException("This view can only be run through its controller");
  }

  private void updateMenu(String id, int ft) {
    mb.removeAll();
    shapes = this.shapesMenu(listener.getIDs());
    mb.add(shapes);
    mb.add(actions);
    frames = framesMenu(id);
    mb.add(frames);
    frameActs = frameActs(ft);
    mb.add(frameActs);
    mb.add(curSitu());
    this.validate();
    this.repaint();
  }

  private void addShape() {
    JTextField id = new JTextField(5);
    JTextField type = new JTextField(5);

    JPanel myPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 20;
    myPanel.add(new JLabel("Please Enter ID and Type (\"rect\" or \"ell\") of shape " +
            "to create."), c);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    myPanel.add(new JLabel("ID:"), c);
    c.gridx = 1;
    c.gridy = 1;
    myPanel.add(id, c);
    c.gridx = 2;
    c.gridy = 1;
    myPanel.add(new JLabel("Type:"), c);
    c.gridx = 3;
    c.gridy = 1;
    myPanel.add(type, c);
    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Creating New Shape or Replacing",
            JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      if (id.getText().matches("^(?=.*[a-zA-Z].*)([a-zA-Z0-9]+)$")) {
        listener.addShape(id.getText(), type.getText());
      } else {
        JOptionPane.showMessageDialog(null,
                "ID must have at least one non-numeric!");
      }
    }

    update();
  }

  private NumberFormatter formatter(int max) {
    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(max);
    formatter.setAllowsInvalid(false);
    return formatter;
  }

  private void addFrame() {
    JPanel myPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    NumberFormatter formatter = formatter(Integer.MAX_VALUE);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridy = 0;
    c.gridx = 0;
    c.gridwidth = 20;
    myPanel.add(new JLabel("Please Enter info of KeyFrame to create & add to the shape. " +
            "Missing any field will cancel addition"), c);
    c.gridwidth = 1;
    c.gridy = 1;
    c.gridx = 0;
    myPanel.add(new JLabel("Time:"), c);
    c.gridx = 1;
    c.gridy = 1;
    JFormattedTextField t = new JFormattedTextField(formatter);
    t.setColumns(5);
    myPanel.add(t, c);
    c.gridx = 2;
    c.gridy = 1;
    myPanel.add(new JLabel("X:"), c);
    c.gridx = 3;
    c.gridy = 1;
    JFormattedTextField x = new JFormattedTextField(formatter);
    x.setColumns(5);
    myPanel.add(x, c);
    c.gridx = 4;
    c.gridy = 1;
    myPanel.add(new JLabel("Y:"), c);
    c.gridx = 5;
    c.gridy = 1;
    JFormattedTextField y = new JFormattedTextField(formatter);
    y.setColumns(5);
    myPanel.add(y, c);
    c.gridx = 6;
    c.gridy = 1;
    myPanel.add(new JLabel("Width:"), c);
    c.gridx = 7;
    c.gridy = 1;
    JFormattedTextField w = new JFormattedTextField(formatter);
    w.setColumns(5);
    myPanel.add(w, c);
    c.gridx = 8;
    c.gridy = 1;
    myPanel.add(new JLabel("Height:"), c);
    c.gridx = 9;
    c.gridy = 1;
    JFormattedTextField h = new JFormattedTextField(formatter);
    h.setColumns(5);
    myPanel.add(h, c);
    c.gridx = 0;
    c.gridy = 2;
    myPanel.add(new JLabel("Red:"), c);
    c.gridx = 1;
    c.gridy = 2;
    formatter = formatter(255);
    JFormattedTextField r = new JFormattedTextField(formatter);
    myPanel.add(r, c);
    c.gridx = 2;
    c.gridy = 2;
    myPanel.add(new JLabel("Green:"), c);
    c.gridx = 3;
    c.gridy = 2;
    JFormattedTextField g = new JFormattedTextField(formatter);
    myPanel.add(g, c);
    c.gridx = 4;
    c.gridy = 2;
    myPanel.add(new JLabel("Blue:"), c);
    c.gridx = 5;
    c.gridy = 2;
    JFormattedTextField b = new JFormattedTextField(formatter);
    myPanel.add(b, c);

    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Adding Frame",
            JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      try {
        listener.addFrame((int) t.getValue(), (int) x.getValue(), (int) y.getValue(),
                (int) w.getValue(), (int) h.getValue(), (int) r.getValue(), (int) g.getValue(),
                (int) b.getValue());
      } catch (NullPointerException e) {
        JOptionPane.showMessageDialog(null,
                "Field can't be empty!");
      }
    }
    update();
  }

  private void modFrame() {
    JPanel myPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    NumberFormatter formatter = formatter(Integer.MAX_VALUE);
    KeyFrame kf = listener.getSF();
    c.gridy = 0;
    c.gridx = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 20;
    myPanel.add(new JLabel("Please Enter new info to change into. " +
            "Missing any field will cancel addition"), c);
    c.gridwidth = 1;
    c.gridx = 0;
    c.gridy = 1;
    myPanel.add(new JLabel("Prev X: " + kf.getX()), c);
    c.gridx = 0;
    c.gridy = 2;
    myPanel.add(new JLabel("X:"), c);
    c.gridx = 1;
    c.gridy = 2;
    JFormattedTextField x = new JFormattedTextField(formatter);
    x.setColumns(5);
    myPanel.add(x, c);
    c.gridx = 2;
    c.gridy = 1;
    myPanel.add(new JLabel("Prev Y: " + kf.getY()), c);
    c.gridx = 2;
    c.gridy = 2;
    myPanel.add(new JLabel("Y:"), c);
    c.gridx = 3;
    c.gridy = 2;
    JFormattedTextField y = new JFormattedTextField(formatter);
    y.setColumns(5);
    myPanel.add(y, c);
    c.gridx = 4;
    c.gridy = 1;
    myPanel.add(new JLabel("Prev Width: " + kf.getWidth()), c);
    c.gridx = 4;
    c.gridy = 2;
    myPanel.add(new JLabel("Width:"), c);
    c.gridx = 5;
    c.gridy = 2;
    JFormattedTextField w = new JFormattedTextField(formatter);
    w.setColumns(5);
    myPanel.add(w, c);
    c.gridx = 6;
    c.gridy = 1;
    myPanel.add(new JLabel("Prev Height: " + kf.getHeight()), c);
    c.gridx = 6;
    c.gridy = 2;
    myPanel.add(new JLabel("Height:"), c);
    c.gridx = 7;
    c.gridy = 2;
    JFormattedTextField h = new JFormattedTextField(formatter);
    h.setColumns(5);
    myPanel.add(h, c);
    c.gridx = 0;
    c.gridy = 3;
    myPanel.add(new JLabel("Prev Red: " + kf.getColor().getRed()), c);
    c.gridx = 0;
    c.gridy = 4;
    myPanel.add(new JLabel("Red:"), c);
    c.gridy = 4;
    c.gridx = 1;
    formatter = formatter(255);
    JFormattedTextField r = new JFormattedTextField(formatter);
    myPanel.add(r, c);
    c.gridx = 2;
    c.gridy = 3;
    myPanel.add(new JLabel("Prev Green: " + kf.getColor().getGreen()), c);
    c.gridx = 2;
    c.gridy = 4;
    myPanel.add(new JLabel("Green:"), c);
    c.gridx = 3;
    c.gridy = 4;
    JFormattedTextField g = new JFormattedTextField(formatter);
    myPanel.add(g, c);
    c.gridx = 4;
    c.gridy = 3;
    myPanel.add(new JLabel("Prev Blue: " + kf.getColor().getBlue()), c);
    c.gridx = 4;
    c.gridy = 4;
    myPanel.add(new JLabel("Blue:"), c);
    c.gridx = 5;
    c.gridy = 4;
    JFormattedTextField b = new JFormattedTextField(formatter);
    myPanel.add(b, c);

    int result = JOptionPane.showConfirmDialog(null, myPanel,
            "Modifying Frame at " + listener.getSelFrame(),
            JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      try {
        listener.addFrame(listener.getSelFrame(), (int) x.getValue(), (int) y.getValue(),
                (int) w.getValue(), (int) h.getValue(), (int) r.getValue(), (int) g.getValue(),
                (int) b.getValue());
      } catch (NullPointerException e) {
        JOptionPane.showMessageDialog(null,
                "Field can't be empty!");
      }
    }
    update();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if (listener.isID(cmd)) {
      listener.selectShape(cmd);
      updateMenu(cmd, listener.getSelFrame());
    } else if (cmd.equalsIgnoreCase("remove shape")) {
      listener.remove();
      updateMenu(listener.getIDs().get(0), listener.getSelFrame());
    } else if (cmd.equalsIgnoreCase("remove frame")) {
      listener.removeFrame();
      updateMenu(listener.getSelected(), listener.getSelFrame());
    } else if (cmd.equalsIgnoreCase("add frame to shape")) {
      addFrame();
    } else if (cmd.equalsIgnoreCase("add/replace shape")) {
      addShape();
    } else if (cmd.equalsIgnoreCase("modify frame")) {
      modFrame();
    } else {
      listener.selectFrame(Integer.parseInt(cmd));
      updateMenu(listener.getSelected(), Integer.parseInt(cmd));
    }
  }
}
