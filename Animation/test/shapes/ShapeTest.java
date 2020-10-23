package shapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;

import keyframe.KeyFrame;
import keyframe.KeyFrameImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * tests for {@link Shape}.
 */
public class ShapeTest {

  private Color col;
  private Color col2;
  private Shape rect;
  private Shape ellie;
  private KeyFrame kyf;
  private KeyFrame kyf2;
  private KeyFrame kyf3;
  private Shape elias;
  private Shape richard;
  private ReadShape shup;

  @Before
  public void setUp() {
    col = new Color(255, 0, 0);
    col2 = new Color(0, 0, 255);
    rect = new Rectangle(1, 200, 200, 50, 100, col);
    ellie = new Ellipse(6, 440, 70, 120, 60, col2);
    kyf = new KeyFrameImpl(200, 200, 50, 100, col);
    kyf2 = new KeyFrameImpl(-200, 300, 50, 100, col);
    kyf3 = new KeyFrameImpl(200, 300, -50, 50, col2);
    elias = new Ellipse(33, kyf);
    richard = new Rectangle(0, kyf3);
    shup = new Rectangle();
  }

  //for testing help
  private boolean sameFrame(KeyFrame f1, KeyFrame f2) {
    return f1.getX() == f2.getX() && f1.getY() == f2.getY() && f1.getHeight() == f2.getHeight()
            && f1.getWidth() == f2.getWidth() && f1.getColor().getRGB() == f2.getColor().getRGB();
  }

  @Test
  public void testTween() {
    rect.addFrame(20, kyf2);
    assertTrue(sameFrame(new KeyFrameImpl(52, 236, 50, 100, col),
            rect.tween(8)));
  }

  @Test
  public void getType_getFrame() {
    assertEquals("rectangle", rect.getType());
    assertEquals("rectangle", richard.getType());
    assertEquals("ellipse", ellie.getType());
    assertEquals("ellipse", elias.getType());
    assertEquals(kyf, elias.getFrame(33));
  }

  /**
   * tests the addFrame, getKeyTimes, and printFrames methods with the rectangle shape. Notably for
   * the getKeyTimes method, tests that the key times are properly sorted from smallest to largest.
   */

  @Test
  public void rect_addFrame_getKeyTimes_printFrames() {
    rect.addFrame(2, kyf);
    rect.addFrame(10, kyf2);
    rect.addFrame(3, kyf3);
    assertEquals(Arrays.asList(1, 2, 3, 10), rect.getKeyTimes());
    assertEquals(Collections.emptyList(), shup.getKeyTimes());
  }

  /**
   * tests the addFrame, getKeyTimes, and printFrames methods with the ellipse shape. Notably for
   * the getKeyTimes method, tests that the key times are properly sorted from smallest to largest.
   */

  @Test
  public void ellipse_addFrame_getKeyTimes_printFrames() {
    ellie.addFrame(800, kyf);
    ellie.addFrame(801, kyf2);
    ellie.addFrame(799, kyf3);
    assertEquals(Arrays.asList(6, 799, 800, 801), ellie.getKeyTimes());
  }

  /**
   * tests the addFrame, getKeyTimes, and removeFrame methods and print methods.
   */

  @Test
  public void ellipse_addFrame_getKeyTimes_removeAllKeyFrames() {
    elias.addFrame(800, kyf);
    elias.addFrame(801, kyf2);
    elias.addFrame(799, kyf3);
    assertEquals(Arrays.asList(33, 799, 800, 801), elias.getKeyTimes());
    elias.removeFrame(800);
    elias.removeFrame(801);
    elias.removeFrame(799);
    elias.removeFrame(33);
    assertEquals(Collections.emptyList(), elias.getKeyTimes());
  }

  /**
   * tests the addFrame, getKeyTimes, removeFrame, and print methods. Notably, tests that the
   * shape's creation time will probably sort itself in the list of getKeyTimes
   */

  @Test
  public void createShape_addFrame_getKeyTimes_removeFrame() {
    Shape shape = new Rectangle(801, 200, 200, 50, 100, col2);
    shape.addFrame(800, kyf);
    shape.addFrame(802, kyf2);
    shape.addFrame(799, kyf3);
    assertEquals(Arrays.asList(799, 800, 801, 802), shape.getKeyTimes());
    shape.removeFrame(802);
    assertEquals(Arrays.asList(799, 800, 801), shape.getKeyTimes());
  }

  /**
   * tests the addFrame, getKeyTimes, printFrames, and removeFrames method. Notably, tests that
   * adding a frame at a tick where one already exists replaces the previous one.
   */

  @Test
  public void replaceFrameAtGivenTick() {
    rect.addFrame(50, kyf);
    assertEquals(Arrays.asList(1, 50), rect.getKeyTimes());
    rect.addFrame(50, kyf2);
    assertEquals(Arrays.asList(1, 50), rect.getKeyTimes());
    rect.addFrame(1, kyf3);
    assertEquals(Arrays.asList(1, 50), rect.getKeyTimes());
    assertEquals(kyf2, rect.getFrame(50));
    rect.removeFrame(50);
    assertEquals(Arrays.asList(1), rect.getKeyTimes());
  }

  /**
   * tests the addFrame, getKeyTimes, getFrame, and printFrames methods.
   */

  @Test
  public void addDifferentShapesAtSameTick() {
    richard.addFrame(30, kyf);
    assertEquals(Arrays.asList(0, 30), richard.getKeyTimes());
    ellie.addFrame(30, kyf2);
    assertEquals(kyf, richard.getFrame(30));
    assertEquals(kyf2, ellie.getFrame(30));
    assertEquals(Arrays.asList(6, 30), ellie.getKeyTimes());


  }

  /**
   * tests all the IllegalArgumentExceptions for the Shape interface's methods with their respective
   * messages.
   */
  @Test
  public void testExceptions() {
    try {
      rect = new Rectangle(-1, 200, 200, 50, 100, col);
    } catch (IllegalArgumentException e) {
      assertEquals("Shape can't be created at negative time", e.getMessage());
    }
    try {
      ellie = new Ellipse(-1, 200, 200, 50, 100, col);
    } catch (IllegalArgumentException e) {
      assertEquals("Shape can't be created at negative time", e.getMessage());
    }
    try {
      rect = new Rectangle(3, 200, 200, 50, 100, null);
    } catch (IllegalArgumentException e) {
      assertEquals("a KeyFrame can't have null color", e.getMessage());
    }
    try {
      ellie = new Ellipse(3, 200, 200, 50, 100, null);
    } catch (IllegalArgumentException e) {
      assertEquals("a KeyFrame can't have null color", e.getMessage());
    }
    try {
      rect = new Rectangle(3, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Can't construct with null keyFrame", e.getMessage());
    }
    try {
      ellie = new Ellipse(3, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Can't construct with null keyFrame", e.getMessage());
    }
    try {
      rect.addFrame(-1, kyf);
    } catch (IllegalArgumentException e) {
      assertEquals("Can't add a frame at negative time", e.getMessage());
    }
    try {
      ellie.addFrame(-1, kyf);
    } catch (IllegalArgumentException e) {
      assertEquals("Can't add a frame at negative time", e.getMessage());
    }
    try {
      rect.addFrame(3, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Can't add null KeyFrame", e.getMessage());
    }
    try {
      ellie.addFrame(3, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Can't add null KeyFrame", e.getMessage());
    }
    try {
      rect.removeFrame(4);
    } catch (IllegalArgumentException e) {
      assertEquals("No KeyFrame at the given time", e.getMessage());
    }
    try {
      ellie.removeFrame(5);
    } catch (IllegalArgumentException e) {
      assertEquals("No KeyFrame at the given time", e.getMessage());
    }
    try {
      rect.getFrame(10);
    } catch (IllegalArgumentException e) {
      assertEquals("No KeyFrame at the given time", e.getMessage());
    }
    try {
      ellie.getFrame(5);
    } catch (IllegalArgumentException e) {
      assertEquals("No KeyFrame at the given time", e.getMessage());
    }
  }
}
