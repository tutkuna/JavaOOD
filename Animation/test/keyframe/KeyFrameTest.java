package keyframe;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;


/**
 * tests for {@link KeyFrame}.
 */
public class KeyFrameTest {

  private Color col;
  private Color col2;
  private KeyFrame kyf;
  private KeyFrame kyf2;

  @Before
  public void setUp() {
    col = new Color(255, 0, 0);
    col2 = new Color(240, 0, 5);
    kyf = new KeyFrameImpl(200, 200, 50, 100, col);
    kyf2 = new KeyFrameImpl(150, 100, 35, 75, col2);
  }

  /**
   * tests all the IllegalArgumentExceptions for the KeyFrame interface's methods.
   */
  @Test
  public void testExceptions() {
    try {
      kyf = new KeyFrameImpl(200, 200, 50, 100, null);
    } catch (IllegalArgumentException e) {
      assertEquals("a KeyFrame can't have null color", e.getMessage());
    }
  }

  @Test
  public void testGetters() {
    assertEquals(col, kyf.getColor());
    assertEquals(200, kyf.getX());
    assertEquals(200, kyf.getY());
    assertEquals(50, kyf.getWidth());
    assertEquals(100, kyf.getHeight());
    assertEquals(col2, kyf2.getColor());
    assertEquals(150, kyf2.getX());
    assertEquals(100, kyf2.getY());
    assertEquals(35, kyf2.getWidth());
    assertEquals(75, kyf2.getHeight());
  }
}