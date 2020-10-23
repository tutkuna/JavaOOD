package cs3500.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.AnimModel;
import model.AnimModelImpl;

import static junit.framework.TestCase.assertEquals;

/**
 * tests for {@link AniView}.
 */
public class AniViewTest {
  private AnimModel model;
  private AniView txt;
  private AniView svg;
  private StringBuilder app;

  @Before
  public void setUp() {
    model = AnimModelImpl.builder().declareShape("ellie", "ellipse")
            .addKeyframe("ellie", 6, 440, 70, 120, 60, 0, 0, 255)
            .addKeyframe("ellie", 33, -10, 10100, 2, 3, 255, 255, 255)
            .declareShape("reggie", "rectangle")
            .addKeyframe("reggie", 1, 200, 200, 50, 100, 255, 0, 0)
            .addKeyframe("reggie", 10, 200, 100, 30, 60, 50, 5, 50)
            .declareShape("motionable", "rectangle")
            .addMotion("motionable", 1, 200, 200, 50, 100, 255, 0,
                    0, 6, 440, 70, 120, 60, 0, 0, 255)
            .setBounds(3, 5, 10, 20).build();
    app = new StringBuilder();
    txt = new TextView(model, app);
    svg = new SVGView(model, app, 20);
  }

  @Test
  public void testRun() {
    assertEquals("", app.toString());
    txt.run();
    assertEquals("shape\tellie\tellipse\n" +
            "motion\tellie\t6\t440\t70\t120\t60\t0\t0\t255\t\t33\t-10\t10100\t2" +
            "\t3\t255\t255\t255\n\n" +
            "shape\tmotionable\trectangle\n" +
            "motion\tmotionable\t1\t200\t200\t50\t100\t255\t0\t0\t\t6" +
            "\t440\t70\t120\t60\t0\t0\t255\n\n" +
            "shape\treggie\trectangle\n" +
            "motion\treggie\t1\t200\t200\t50\t100\t255\t0\t0\t\t10\t200\t100\t30\t60" +
            "\t50\t5\t50\n\n", app.toString());
    app.delete(0, app.length());
    assertEquals("", app.toString());
    svg.run();
    assertEquals("<svg viewBox=\"3 5 10 20\" version=\"1.1\" xmlns=" +
            "\"http://www.w3.org/2000/svg\" >\n" +
            "<ellipse id=\"ellie\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" " +
            "fill=\"rgb(0, 0, 255)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1ms\" attributeName=" +
            "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1350ms\" attributeName=" +
            "\"cx\" from=\"500\" to=\"-9\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1350ms\" attributeName=" +
            "\"cy\" from=\"100\" to=\"10101\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1350ms\" attributeName=" +
            "\"rx\" from=\"60\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1350ms\" attributeName=" +
            "\"ry\" from=\"30\" to=\"1\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1350ms\" attributeName=" +
            "\"fill\" from=\"rgb(0, 0, 255)\" to=\"rgb(255, 255, 255)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1650ms\" dur=\"1ms\" attributeName=" +
            "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<rect id=\"motionable\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" " +
            "fill=\"rgb(255, 0, 0)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"1ms\" attributeName=" +
            "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"250ms\" attributeName=" +
            "\"x\" from=\"200\" to=\"440\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"250ms\" attributeName=" +
            "\"y\" from=\"200\" to=\"70\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"250ms\" attributeName=" +
            "\"width\" from=\"50\" to=\"120\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"250ms\" attributeName=" +
            "\"height\" from=\"100\" to=\"60\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"250ms\" attributeName=" +
            "\"fill\" from=\"rgb(255, 0, 0)\" to=\"rgb(0, 0, 255)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"300ms\" dur=\"1ms\" attributeName=" +
            "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<rect id=\"reggie\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=" +
            "\"rgb(255, 0, 0)\" visibility=\"hidden\" >\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"1ms\" attributeName=" +
            "\"visibility\" from=\"hidden\" to=\"visible\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"450ms\" attributeName=" +
            "\"y\" from=\"200\" to=\"100\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"450ms\" attributeName=" +
            "\"width\" from=\"50\" to=\"30\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"450ms\" attributeName=" +
            "\"height\" from=\"100\" to=\"60\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"50ms\" dur=\"450ms\" attributeName=" +
            "\"fill\" from=\"rgb(255, 0, 0)\" to=\"rgb(50, 5, 50)\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500ms\" dur=\"1ms\" attributeName=" +
            "\"visibility\" from=\"visible\" to=\"hidden\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "</svg>", app.toString());
  }

  @Test
  public void testExceptions() {
    String msg = "";
    try {
      txt = new TextView(null, app);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in", msg);
    msg = "";
    try {
      txt = new TextView(model, null);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in", msg);
    msg = "";
    try {
      txt = new TextView(null, null);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in", msg);
    msg = "";
    try {
      svg = new SVGView(null, app, 20);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in or tps set <= 0", msg);
    msg = "";
    try {
      svg = new SVGView(model, null, 20);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in or tps set <= 0", msg);
    msg = "";
    try {
      svg = new SVGView(null, null, 20);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in or tps set <= 0", msg);
    msg = "";
    try {
      svg = new SVGView(model, app, -5);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in or tps set <= 0", msg);
    msg = "";
    try {
      svg = new SVGView(model, app, 0);
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("null passed in or tps set <= 0", msg);
    Appendable badapp = new Appendable() {
      @Override
      public Appendable append(CharSequence csq) throws IOException {
        throw new IOException();
      }

      @Override
      public Appendable append(CharSequence csq, int start, int end) throws IOException {
        throw new IOException();
      }

      @Override
      public Appendable append(char c) throws IOException {
        throw new IOException();
      }
    };
    msg = "";
    svg = new SVGView(model, badapp, 3);
    try {
      svg.run();
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("Can't append to given appendable", msg);
    msg = "";
    txt = new TextView(model, badapp);
    try {
      txt.run();
    } catch (IllegalArgumentException e) {
      msg = e.getMessage();
    }
    assertEquals("Not appendable", msg);
  }
}