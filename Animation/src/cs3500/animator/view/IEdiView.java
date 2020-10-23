package cs3500.animator.view;

import java.awt.event.KeyListener;
import java.util.ArrayList;

import cs3500.animator.controller.EdiFuncs;
import shapes.ReadShape;

public interface IEdiView extends AniView {
  void setListener(EdiFuncs list);

  void setSituation();

  void update();

  void draw(ArrayList<ReadShape> shapes, int t);

  void addKeyListener(KeyListener e);
}
