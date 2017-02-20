package vn.com.phongnguyen93.wazzup;

import java.util.ArrayList;

/**
 * Created by phongnguyen on 2/19/17.
 */

public class MovieTrailerList {
  private int id;
  private ArrayList<MovieTrailer> results;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ArrayList<MovieTrailer> getResults() {
    return results;
  }

  public void setResults(ArrayList<MovieTrailer> results) {
    this.results = results;
  }

  public class MovieTrailer {
    private String id;
    private String key;
    private String name;
    private String site;
    private int size;
    private String type;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getSite() {
      return site;
    }

    public void setSite(String site) {
      this.site = site;
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }
}
