package cs3500.animator.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import cs3500.animator.view.EdiHelp;
import cs3500.animator.view.IEdiView;
import keyframe.KeyFrame;
import keyframe.KeyFrameImpl;
import model.EdiModel;
import shapes.Ellipse;
import shapes.ReadShape;
import shapes.Rectangle;

public class EdiCont implements AniCont, EdiFuncs {
  private IEdiView view;
  private EdiModel model;
  private int tps;
  private int tick;
  private boolean paused;
  private boolean loop;
  private boolean start;
  private String selected;
  private int selFrame;

  public EdiCont(IEdiView view, EdiModel model, int tps) {
    this.view = view;
    this.model = model;
    this.tps = tps;
    this.tick = 0;
    if (hasShapes()) {
      selected = model.getIDs().get(0);
      if (hasFrames()) {
        selFrame = model.getShape(selected).getKeyTimes().get(0);
      }
    }
    EdiHelp eh = new EdiHelp(this);
    view.addKeyListener(eh);
    view.setListener(this);
    view.setSituation();
    paused = false;
    loop = false;
    start = false;
  }

  @Override
  public void speedUp() {
    tps++;
    view.update();
  }

  @Override
  public void speedDown() {
    if (tps != 1) {
      tps--;
      view.update();
    }
  }

  @Override
  public boolean done() {
    int last = 0;
    try {
      for (ReadShape s : model.readAll()) {
        ArrayList<Integer> temp = s.getKeyTimes();
        try {
          last = Math.max(temp.get(temp.size() - 1), last);
        } catch (IndexOutOfBoundsException e) {
          // when you remove a shape through removeFrame, it stays for one call and throws error.
        }
      }
      return tick == last;
    } catch (ConcurrentModificationException e) {
      return false; // remove action conflicted with when this was called,
      // ignore this call and stop at next iteration
    }
  }


  @Override
  public void togglePause() {
    paused = !paused;
    view.update();
  }

  @Override
  public void restart() {
    tick = 0;
  }

  @Override
  public void toggleLoop() {
    loop = !loop;
    view.update();
  }

  @Override
  public void start() {
    if (!start) {
      start = true;
      view.update();
    }
  }

  private boolean hasFrames() {
    return !model.getShape(selected).getKeyTimes().isEmpty();
  }

  private boolean hasShapes() {
    return !model.getIDs().isEmpty();
  }

  @Override
  public void selectShape(String id) {
    selected = id;

    if (hasFrames()) {
      selFrame = model.getShape(selected).getKeyTimes().get(0);
    }
  }

  public void selectFrame(int tick) {
    selFrame = tick;
  }

  public ArrayList<ReadShape> readAll() {
    return model.readAll();
  }

  @Override
  public ArrayList<String> getIDs() {
    return model.getIDs();
  }

  @Override
  public ReadShape getShape(String id) {
    return model.getShape(id);
  }

  @Override
  public int getBound(String bound) {
    return model.getBounds(bound);
  }

  @Override
  public Dimension getMax() {
    return model.getMax();
  }

  @Override
  public boolean paused() {
    return paused;
  }

  @Override
  public boolean looping() {
    return loop;
  }

  @Override
  public int speed() {
    return tps;
  }

  @Override
  public void addShape(String id, String type) {
    switch (type) {
      case "rect":
        model.addShape(new Rectangle(), id);
        break;
      case "ell":
        model.addShape(new Ellipse(), id);
        break;
      default:
        // do nothing
    }
  }

  @Override
  public void addFrame(int time, int x, int y, int w, int h, int r, int g, int b) {
    KeyFrame kf = new KeyFrameImpl(x, y, w, h, new Color(r, g, b));
    model.addKeyFrame(selected, time, kf);
  }

  @Override
  public KeyFrame getSF() {
    return model.getShape(selected).getFrame(selFrame);
  }

  @Override
  public void remove() {
    model.removeShape(selected);
    if (hasShapes()) {
      selected = model.getIDs().get(0);
      selFrame = model.getShape(selected).getKeyTimes().get(0);
    }
  }

  @Override
  public void removeFrame() {
    if (!hasFrames()) {
      return;
    }
    model.getEdiShape(selected).removeFrame(selFrame);
    if (hasFrames()) {
      selFrame = model.getShape(selected).getKeyTimes().get(0);
    }
  }

  @Override
  public boolean isID(String id) {
    return model.getIDs().contains(id);
  }

  @Override
  public String getSelected() {
    return selected;
  }

  @Override
  public int getSelFrame() {
    return selFrame;
  }

  @Override
  public void run() {
    while (!this.done()) {
      if (start) {
        try {
          if (!paused) {
            view.draw(model.readAll(), tick);
            tick++;
            Thread.sleep(1000 / tps);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    if (this.loop) {
      this.restart();
      this.run();
    }
  }
}
