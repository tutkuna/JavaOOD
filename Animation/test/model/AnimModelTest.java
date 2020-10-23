package model;


import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import keyframe.KeyFrame;
import keyframe.KeyFrameImpl;
import shapes.Ellipse;
import shapes.ReadShape;
import shapes.Rectangle;
import shapes.Shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * tests for {@link AnimModel}.
 */
public class AnimModelTest {
  private AnimModelImpl.Builder bld = AnimModelImpl.builder();
  private ReadShape rect;
  private ReadShape ellie;
  private AnimModel model;
  private Shape motionable;

  @Before
  public void setUp() {
    model = AnimModelImpl.builder().declareShape("ellie", "ellipse")
            .addKeyframe("ellie", 6, 440, 70, 120, 60, 0, 0, 255)
            .declareShape("reggie", "rectangle")
            .addKeyframe("reggie", 1, 200, 200, 50, 100, 255, 0, 0)
            .declareShape("motionable", "rectangle")
            .addMotion("motionable", 1, 200, 200, 50, 100, 255, 0,
                    0, 6, 440, 70, 120, 60, 0, 0, 255)
            .setBounds(3, 5, 10, 20).build();
    Color col = new Color(255, 0, 0);
    Color col2 = new Color(0, 0, 255);
    rect = new Rectangle(1, 200, 200, 50, 100, col);
    ellie = new Ellipse(6, 440, 70, 120, 60, col2);
    motionable = new Rectangle(1, 200, 200, 50, 100,
            new Color(255, 0, 0));
    motionable.addFrame(6, new KeyFrameImpl(440, 70, 120, 60,
            new Color(0, 0, 255)));
  }

  //for testing help
  private boolean equalShape(ReadShape s1, ReadShape s2) {
    ArrayList<Integer> t1 = s1.getKeyTimes();
    ArrayList<Integer> t2 = s2.getKeyTimes();
    if (t1.size() != t2.size()) {
      return false;
    }
    boolean bol = true;
    for (int t : t1) {
      bol &= sameFrame(s1.getFrame(t), s2.getFrame(t));
    }
    return s1.getType().equalsIgnoreCase(s2.getType()) && bol;
  }

  //for testing help
  private boolean sameFrame(KeyFrame f1, KeyFrame f2) {
    return f1.getX() == f2.getX() && f1.getY() == f2.getY() && f1.getHeight() == f2.getHeight()
            && f1.getWidth() == f2.getWidth() && f1.getColor().getRGB() == f2.getColor().getRGB();
  }

  //for testing help
  private boolean sameListShape(ArrayList<ReadShape> l1, ArrayList<ReadShape> l2) {
    for (ReadShape s : l1) {
      boolean bol = false;
      for (ReadShape s2 : l2) {
        try {
          bol |= equalShape(s, s2);
        } catch (IllegalArgumentException e) {
          //ignore
        }
      }
      if (!bol) {
        return false;
      }
    }
    return true;
  }

  @Test
  public void testBuilderMethods_getShape_getBounds_getMax_getIDs_readAll() {
    ArrayList<ReadShape> shapes = new ArrayList<>(Arrays.asList(motionable, rect, ellie));
    ArrayList<ReadShape> actual = model.readAll();
    assertTrue(sameListShape(shapes, actual));
    assertEquals(Collections.emptyList(), bld.build().readAll());
    assertTrue(equalShape(ellie, model.getShape("ellie")));
    assertTrue(equalShape(rect, model.getShape("reggie")));
    assertTrue(equalShape(motionable, model.getShape("motionable")));
    assertEquals(3, model.getBounds("x"));
    assertEquals(5, model.getBounds("y"));
    assertEquals(10, model.getBounds("w"));
    assertEquals(20, model.getBounds("h"));
    assertEquals(new Dimension(560, 300), model.getMax());
    for (String s : model.getIDs()) {
      assertTrue(Arrays.asList("motionable", "reggie", "ellie").contains(s));
    }
  }

  @Test
  public void testExceptions() {
    String msg = "";
    try {
      bld.addKeyframe("ellie", 6, 440, 70, 120, 60, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("No such shape", msg);
    try {
      bld.declareShape("reg", "triangle");
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("No such shape type", msg);
    bld.declareShape("reg", "rectangle");
    try {
      bld.addKeyframe("reg", -1, 440, 70, 120, 60, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("Can't add keyFrame at negative time", msg);
    msg = "";
    try {
      bld.addMotion("reg", -1, 440, 70, 120, 60, 0, 0, 255,
              6, 440, 70, 120, 60, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("Can't add keyFrame at negative time", msg);
    msg = "";
    try {
      bld.addMotion("reg", 6, 440, 70, 120, 60, 0, 0, 255,
              -1, 440, 70, 120, 60, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("Can't add keyFrame at negative time", msg);
    msg = "";
    try {
      bld.addMotion("reg", -3, 440, 70, 120, 60, 0, 0, 255,
              -1, 440, 70, 120, 60, 0, 0, 255);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("Can't add keyFrame at negative time", msg);
    try {
      model.getBounds("f");
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("no such bound", msg);
  }
}

