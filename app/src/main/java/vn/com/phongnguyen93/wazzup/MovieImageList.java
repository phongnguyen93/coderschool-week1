package vn.com.phongnguyen93.wazzup;

import java.util.ArrayList;

/**
 * Created by phongnguyen on 2/19/17.
 */

public class MovieImageList {
  private int id;
  private ArrayList<MovieImage> backdrops ;
  private ArrayList<MovieImage> posters;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ArrayList<MovieImage>  getBackdrops() {
    return backdrops;
  }

  public void setBackdrops(ArrayList<MovieImage>  backdrops) {
    this.backdrops = backdrops;
  }

  public ArrayList<MovieImage>  getPosters() {
    return posters;
  }

  public void setPosters(ArrayList<MovieImage>  posters) {
    this.posters = posters;
  }

  public class MovieImage {
    //private double aspect_ratio;
    private String file_path;
    private int height;
    //private String iso_639_1;
    //private int vote_average;
    //private int vote_count;
    private int width;

    //public double getAspectRatio() {
    //  return aspect_ratio;
    //}
    //
    //public void setAspectRatio(double aspect_ratio) {
    //  this.aspect_ratio = aspect_ratio;
    //}

    public String getFilePath() {
      return file_path;
    }

    public void setFilePath(String file_path) {
      this.file_path = file_path;
    }

    public int getHeight() {
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }

    //public String getIso6391() {
    //  return iso_639_1;
    //}
    //
    //public void setIso6391(String iso_639_1) {
    //  this.iso_639_1 = iso_639_1;
    //}
    //
    //public int getVoteAverage() {
    //  return vote_average;
    //}
    //
    //public void setVoteAverage(int vote_average) {
    //  this.vote_average = vote_average;
    //}
    //
    //public int getVoteCount() {
    //  return vote_count;
    //}
    //
    //public void setVoteCount(int vote_count) {
    //  this.vote_count = vote_count;
    //}

    public int getWidth() {
      return width;
    }

    public void setWidth(int width) {
      this.width = width;
    }
  }
}
