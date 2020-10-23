////
//// YOU SHOULD NOT MODIFY THIS FILE
////
//// (You don't need to submit it, either.)
////

package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link Book}s.
 */
public class BookTest {
  Publication rushdie = new Book("Midnight's Children", "Salman Rushdie",
                                 "Jonathan Cape", "London", 1980);

  @Test
  public void testCiteApa() {
    assertEquals("Salman Rushdie (1980). Midnight's Children. "
                   + "London: Jonathan Cape.",
                 rushdie.citeApa());
    String temp = null;
  }

  @Test
  public void testCiteMla() {
    assertEquals("Salman Rushdie. Midnight's Children. London: "
                   + "Jonathan Cape, 1980.",
                 rushdie.citeMla());
  }
}
