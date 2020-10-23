package cs3500.hw01.publication;

/**
 * Represents bibliographic information for Webpages.
 */
public class Webpage implements Publication {
  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * Constructs a webpage.
   *
   * @param title     title of webpage
   * @param url       url of webpage
   * @param retrieved date retrieved
   */
  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }

  @Override
  public String citeApa() {
    return title + ". Retrieved " + retrieved + ", from " + url + ".";
  }

  @Override
  public String citeMla() {
    return "\"" + title + ".\" Web. " + retrieved + " <" + url + ">.";
  }
}
