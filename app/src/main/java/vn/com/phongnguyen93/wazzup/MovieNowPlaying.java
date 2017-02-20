package vn.com.phongnguyen93.wazzup;

import java.util.ArrayList;

/**
 * Created by phongnguyen on 2/16/17.
 */

public class MovieNowPlaying {
  private int page;
  private ArrayList<Movie> results;
  private NowPlayingDate dates;
  private int total_pages;
  private int total_results;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public ArrayList<Movie> getResults() {
    return results;
  }

  public void setResults(ArrayList<Movie> results) {
    this.results = results;
  }

  public NowPlayingDate getDates() {
    return dates;
  }

  public void setDates(NowPlayingDate dates) {
    this.dates = dates;
  }

  public int getTotalPages() {
    return total_pages;
  }

  public void setTotalPages(int total_pages) {
    this.total_pages = total_pages;
  }

  public int getTotalResults() {
    return total_results;
  }

  public void setTotalResults(int total_results) {
    this.total_results = total_results;
  }

  public class NowPlayingDate {
    private String maximum;
    private String minimum;

    public String getMaximum() {
      return maximum;
    }

    public void setMaximum(String maximum) {
      this.maximum = maximum;
    }

    public String getMinimum() {
      return minimum;
    }

    public void setMinimum(String minimum) {
      this.minimum = minimum;
    }
  }
}
