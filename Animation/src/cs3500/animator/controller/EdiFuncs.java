package cs3500.animator.controller;

import java.awt.Dimension;
import java.util.ArrayList;

import keyframe.KeyFrame;
import shapes.ReadShape;

public interface EdiFuncs {
  void speedUp();

  void speedDown();

  boolean done();

  void restart();

  void togglePause();

  void toggleLoop();

  void start();

  void selectShape(String id);

  boolean isID(String id);

  void remove();

  void removeFrame();

  void selectFrame(int t);

  String getSelected();

  int getSelFrame();

  ArrayList<ReadShape> readAll();

  ArrayList<String> getIDs();

  ReadShape getShape(String id);

  int getBound(String bound);

  Dimension getMax();

  boolean paused();

  boolean looping();

  int speed();

  void addShape(String id, String type);

  void addFrame(int time, int x, int y, int w, int h, int r, int g, int b);

  KeyFrame getSF();
}
