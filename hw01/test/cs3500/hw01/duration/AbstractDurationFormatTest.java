package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the format method of {@link Duration}s. Add your tests to this class to assure that
 * your format method works properly
 */
public abstract class AbstractDurationFormatTest {
  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
            hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
            hms(4, 5, 17).format("%h:%M:%S"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormat1() {
    hms(5, 5, 5).format(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormat2() {
    hms(5, 5, 5).format("hello_%g_whups");
  }

  @Test
  public void testFormat3() {
    assertEquals("%t15.4.eyo",
            hms(1, 4, 16).format("%%t15.%m.eyo"));
  }

  @Test
  public void testFormat4() {
    assertEquals("%t15.04.eyo",
            hms(1, 1, 220).format("%%t15.%M.eyo"));
  }

  @Test
  public void testFormat5() {
    assertEquals("t.1.eyo",
            sec(3880).format("t.%h.eyo"));
  }

  @Test
  public void testFormat6() {
    assertEquals("", sec(0).format(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormat7() {
    sec(0).format("%");
  }

  @Test
  public void testFormat8() {
    assertEquals("%t15.40.eyo",
            hms(1, 1, 220).format("%%t15.%S.eyo"));
  }

  @Test
  public void testFormat9() {
    assertEquals("Hello World",
            sec(1500).format("Hello World"));
  }


  // ADD MORE TESTS HERE
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"




  

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */

  /**
   * Constructs an instance of the class under test representing the duration given in hours,
   * minutes, and seconds.
   *
   * @param hours   the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  /**
   * Said do not touch, but docks point if I don't add this comment.
   */
  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  /**
   * Said do not touch, but docks point if I don't add this comment.
   */
  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}
